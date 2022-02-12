object Plugins {
    object Spotless {
        const val version = "6.2.2"
        const val id = "com.diffplug.spotless"
    }

    object Kotlin {
        const val gradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.kotlinVersion}"
    }

    object Android {
        private const val version = "7.1.1"

        const val gradle = "com.android.tools.build:gradle:$version"
    }
}