import dev.mkeeda.spaceship.buildsrc.Libs

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 26
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
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
}

dependencies {
    implementation(project(":domain"))

    api(Libs.Ktor.clientCio)
    implementation(Libs.Ktor.contentNegotiation)
    implementation(Libs.Ktor.kotlinxJson)
    implementation(Libs.Ktor.Logging.base)
    implementation(Libs.Ktor.Logging.slf4JImpl)

    implementation(Libs.Dagger.Hilt.base)
    kapt(Libs.Dagger.Hilt.compiler)

    implementation(Libs.AndroidX.Paging.runtime)
    implementation(Libs.AndroidX.DataStore.preferences)
    implementation(Libs.AndroidX.DataStore.dataStoreCore)
    implementation(Libs.Tink.android)
}
