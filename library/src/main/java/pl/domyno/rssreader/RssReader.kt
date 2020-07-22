package pl.domyno.rssreader

import kotlinx.coroutines.*
import okhttp3.*
import pl.domyno.rssparser.Feed
import pl.domyno.rssparser.FeedParser
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Provides simple interface for reading RSS/Atom feeds. Uses https://github.com/domyn/rssparser as
 * parsing engine and coroutines for managing tasks in background.
 *
 * @property parser FeedParser instance for parsing feed.
 * @property okHttpClient OkHttpClient instance for loading feed.
 */
class RssReader(private val parser: FeedParser,
                private val okHttpClient: OkHttpClient = OkHttpClient()) {

    /**
     * Load RSS/Atom feed. The operation is handled asynchronously in background thread.
     *
     * @param url URL to feed.
     * @return List of feeds
     */
    @Suppress("MemberVisibilityCanBePrivate")
    suspend fun loadFeed(url: String): List<Feed> {
        return coroutineScope { async(Dispatchers.IO) {
            val request = Request.Builder().url(url).build()

            return@async suspendCoroutine<List<Feed>> {
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        it.resumeWithException(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.use { _ ->
                            if (response.isSuccessful && response.body() != null)
                                 it.resume(parser.parse(response.body()!!.string()))
                            else
                                it.resumeWithException(
                                        IOException("Feed load error, code ${response.code()}, body ${if (response.body() == null) "was" else "was not"} null")
                                )
                        }
                    }
                })
            }
        }.await() }
    }

    /**
     * Loads feed with blocking current thread.
     *
     * @param url URL of feed to load
     * @return list of feeds
     */
    fun loadFeedBlocking(url: String) = runBlocking { loadFeed(url) }

    companion object {
        /**
         * Default reader for Atom feeds.
         */
        val atomReader = RssReader(FeedParser.atom())

        /**
         * Default reader for RSS feeds.
         */
        val rssReader = RssReader(FeedParser.rss())
    }
}
