// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.72"))
        classpath("com.android.tools.build", "gradle", "4.0.1")
        classpath("com.github.dcendents","android-maven-gradle-plugin", "2.1")
        classpath("com.jfrog.bintray.gradle", "gradle-bintray-plugin", "1.+")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
