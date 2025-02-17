plugins {
    alias(libs.plugins.astracrypt.android.library)
    alias(libs.plugins.astracrypt.android.library.compose)
    alias(libs.plugins.astracrypt.kotlin.coroutines)
    alias(libs.plugins.astracrypt.android.hilt)
    alias(libs.plugins.astracrypt.android.hilt.compose)
    alias(libs.plugins.astracrypt.android.paging)
    alias(libs.plugins.astracrypt.android.work)
}

android {
    namespace = "com.nevidimka655.notes"
}

dependencies {
    implementation(projects.features.notes.domain)
    implementation(projects.features.notes.di)

    implementation(projects.core.utils)
    implementation(projects.core.resources)
}