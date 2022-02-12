plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    buildToolsVersion = "32.1.0-rc1"

    defaultConfig {
        applicationId = "com.github.whyrising.androiddevchallenge"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = Ci.publishVersion
    }

    signingConfigs {
        // We use a bundled debug keystore, to allow debug builds from CI to
        // be upgradable
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Libs.jvmTarget
//        useIR = true
    }

    buildFeatures {
        compose = true

        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.version
    }

    packagingOptions {
        // Multiple dependency bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        resources.excludes += "/META-INF/AL2.0"
        resources.excludes += "/META-INF/LGPL2.1"
    }
}

dependencies {
    implementation(Libs.AndroidMaterial.material)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.navigation)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.uiTooling)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.iconsCore)
    implementation(Libs.Compose.iconsExt)
    implementation(Libs.Compose.activity)
    implementation(Libs.Compose.viewModels)
    implementation(Libs.Kotlinx.coroutines)
    implementation("androidx.core:core-splashscreen:1.0.0-beta01")

    debugImplementation(Libs.LayoutInspector.uiTooling)
    debugImplementation(Libs.LayoutInspector.reflect)

    testImplementation(Libs.Kotest.runner)
    testImplementation(Libs.Kotest.assertions)
    testImplementation(Libs.Kotest.property)

    testImplementation(Libs.Kotlinx.coroutinesTest)

    testImplementation(Libs.Compose.uiTestJUnit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
