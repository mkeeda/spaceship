import com.android.build.api.dsl.LibraryBuildType
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import dev.mkeeda.spaceship.buildsrc.Libs
import org.jetbrains.kotlin.konan.properties.hasProperty

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
        debug {
            setKintoneCredentials()
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            setKintoneCredentials()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun LibraryBuildType.setKintoneCredentials() {
    gradleLocalProperties(rootDir).let {
        val kintoneDomain =
            if (it.hasProperty("kintoneDomain")) it.getProperty("kintoneDomain") else "\"\""
        val kintoneUsername =
            if (it.hasProperty("kintoneUsername")) it.getProperty("kintoneUsername") else "\"\""
        val kintonePassword =
            if (it.hasProperty("kintonePassword")) it.getProperty("kintonePassword") else "\"\""
        buildConfigField("String", "kintoneDomain", kintoneDomain)
        buildConfigField("String", "kintoneUsername", kintoneUsername)
        buildConfigField("String", "kintonePassword", kintonePassword)
    }
}

dependencies {
    implementation(project(":domain"))

    api(Libs.Ktor.clientOkHttp)
    implementation(Libs.Ktor.contentNegotiation)
    implementation(Libs.Ktor.kotlinxJson)
    implementation(Libs.Ktor.Logging.base)
    implementation(Libs.Ktor.Logging.slf4JImpl)

    implementation(Libs.Dagger.Hilt.base)
    kapt(Libs.Dagger.Hilt.compiler)

    implementation(Libs.AndroidX.Paging.runtime)
}
