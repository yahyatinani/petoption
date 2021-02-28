object Plugins {
    object Spotless {
        const val version = "5.7.0"
        const val id = "com.diffplug.spotless"
    }

    object Kotlin {
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.kotlinVersion}"
    }

    object Android {
        private const val version = "7.0.0-alpha08"

        const val gradle = "com.android.tools.build:gradle:$version"
    }
}