plugins {
    alias(libs.plugins.astracrypt.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.nevidimka655.notes"

    defaultConfig {
        buildFeatures.compose = true
    }
}

dependencies {
    api(projects.domain.notes)
    api(projects.ui.composeCore)

    // Hilt
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    api(libs.hilt.android)

    // Room
    api(libs.room.runtime)
    api(libs.room.ktx)
    api(libs.room.paging)
    ksp(libs.room.compiler)
}