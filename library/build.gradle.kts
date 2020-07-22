plugins {
    id("com.android.library")
    kotlin("android")
}

val groupId by extra("pl.domyno")
val artifactId by extra("rssreader-android")

val versionMajor = 1
val versionMinor = 0
val versionPatch = 5
val versionCode = 1000 * versionMajor + 100 * versionMinor + versionPatch
val versionName = "$versionMajor.$versionMinor.$versionPatch"

// bintray config
val bintrayRepo by extra("maven")
val bintrayName by extra(artifactId)
val publishedGroupId by extra(groupId)
val libraryName by extra(artifactId)
val artifact by extra(artifactId)
val libraryVersion by extra(versionName)
val libraryDescription by extra("RSS/Atom reader library for Android")
val siteUrl by extra("https://github.com/domyn/rssreader-android")
val gitUrl by extra("https://github.com/domyn/rssreader-android.git")
val developerId by extra("domyn")
val developerName by extra("Dominik Murzynowski")
val developerEmail by extra("github@domyno.pl")
val licenseName by extra("The Apache Software License, Version 2.0")
val licenseUrl by extra("http://www.apache.org/licenses/LICENSE-2.0.txt")
val allLicenses by extra(arrayOf("Apache-2.0"))

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = versionCode
        versionName = versionName
        setConsumerProguardFiles(listOf("proguard-rules.pro"))

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    lintOptions {
        isCheckReleaseBuilds = true
        isAbortOnError = true
        isCheckGeneratedSources = true
    }

    packagingOptions {
        exclude("META-INF/**")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.3.8")
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-android", "1.3.8")
    api("pl.domyno", "rssparser", "1.0.0")
    api("com.squareup.okhttp3", "okhttp", "4.+")


    testImplementation("junit", "junit", "4.12")
    androidTestImplementation("com.android.support.test", "runner", "1.0.2")

}

apply {
    from("https://raw.githubusercontent.com/numetriclabz/jcenter/master/installv.gradle")
    from("https://raw.githubusercontent.com/domyn/jcenter/master/bintrayv.gradle")
}
