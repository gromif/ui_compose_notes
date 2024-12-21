plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.nevidimka655.notes"
    compileSdk = project.property("compileSdk").toString().toInt()

    defaultConfig {
        minSdk = project.property("minSdk").toString().toInt()
        consumerProguardFiles("consumer-rules.pro")

        buildFeatures.compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    jvmToolchain(project.property("kotlinJvmToolchainVersion").toString().toInt())
}

dependencies {
    api(project(mapOf("path" to ":ui:compose-core")))

    // Hilt
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    api(libs.hilt.android)

    // Paging
    api(libs.paging.compose)
    api(libs.paging.runtime.ktx)

    // Room
    api(libs.room.runtime)
    api(libs.room.ktx)
    api(libs.room.paging)
    ksp(libs.room.compiler)
}