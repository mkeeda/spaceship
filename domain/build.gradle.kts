import dev.mkeeda.spaceship.buildsrc.Libs

plugins {
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":data"))

    implementation(Libs.KotlinX.coroutines)

    implementation(Libs.Dagger.base)

    implementation(Libs.AndroidX.Paging.common)
}
