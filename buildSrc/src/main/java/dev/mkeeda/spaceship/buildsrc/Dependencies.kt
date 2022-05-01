package dev.mkeeda.spaceship.buildsrc

object GradlePlugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:1.6.10"
    const val android = "com.android.tools.build:gradle:7.2.0-beta01"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Libs.Dagger.version}"
    const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:5.15.2"
}

object Libs {
    object KotlinX {
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.0"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2"
    }

    object Ktor {
        private const val version = "1.6.7"
        const val clientOkHttp = "io.ktor:ktor-client-okhttp:$version"
        const val clientSerialization = "io.ktor:ktor-client-serialization:$version"

        object Logging {
            const val base = "io.ktor:ktor-client-logging:$version"
            const val slf4JImpl = "ch.qos.logback:logback-classic:1.2.5"
        }
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        const val navigationCompose = "androidx.navigation:navigation-compose:2.4.0-rc01"
        const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.0.0-rc02"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"

        object Hilt {
            const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-rc01"
        }

        object Compose {
            const val compilerVersion = "1.2.0-alpha08"
            const val compiler = "androidx.compose.compiler:compiler:$compilerVersion"
            const val material = "androidx.compose.material:material:1.2.0-alpha08"

            object Ui {
                private const val version = "1.2.0-alpha08"
                const val ui = "androidx.compose.ui:ui:$version"
                const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
                const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
                const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
            }
        }

        object Paging {
            private const val version = "3.1.0"
            const val common = "androidx.paging:paging-common:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
            const val compose = "androidx.paging:paging-compose:1.0.0-alpha14"
        }

        object Test {
            const val extJunit = "androidx.test.ext:junit:1.1.3"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }
    }

    object Accompanist {
        private const val version = "0.22.1-rc"
        const val inset = "com.google.accompanist:accompanist-insets:$version"
        const val insetUi = "com.google.accompanist:accompanist-insets-ui:$version"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
    }

    object Dagger {
        const val version = "2.38.1"
        const val base = "com.google.dagger:dagger:$version"

        object Hilt {
            const val base = "com.google.dagger:hilt-android:$version"
            const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        }
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
    }
}
