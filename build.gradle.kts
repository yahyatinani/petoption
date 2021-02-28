buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.Android.gradle)
        classpath(Plugins.Kotlin.gradle)
    }
}

plugins {
    id(Plugins.Spotless.id) version Plugins.Spotless.version
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://kotlin.bintray.com/kotlinx")
        }
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = Plugins.Spotless.id)

    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint("0.40.0")
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
    }
}
