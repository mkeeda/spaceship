import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import dev.mkeeda.spaceship.buildsrc.Libs

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 26
        targetSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            gradleLocalProperties(rootDir).let {
                val kintoneDomain = it.getProperty("kintoneDomain")
                val kintoneUsername = it.getProperty("kintoneUsername")
                val kintonePassword = it.getProperty("kintonePassword")
                buildConfigField("String", "kintoneDomain", kintoneDomain)
                buildConfigField("String", "kintoneUsername", kintoneUsername)
                buildConfigField("String", "kintonePassword", kintonePassword)
            }
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
}

dependencies {
    implementation(project(":domain"))

    api(Libs.Ktor.clientOkHttp)
    implementation(Libs.Ktor.clientSerialization)
    implementation(Libs.Ktor.Logging.base)
    implementation(Libs.Ktor.Logging.slf4JImpl)

    implementation(Libs.Dagger.Hilt.base)
    kapt(Libs.Dagger.Hilt.compiler)
}
