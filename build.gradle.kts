plugins {
    alias(libs.plugins.astracrypt.android.library)
    alias(libs.plugins.astracrypt.android.library.compose)
    alias(libs.plugins.astracrypt.kotlin.coroutines)
    alias(libs.plugins.astracrypt.android.hilt)
    alias(libs.plugins.astracrypt.android.hilt.compose)
    alias(libs.plugins.astracrypt.android.paging)
}

android {
    namespace = "com.nevidimka655.notes"
}

dependencies {
    implementation(projects.domain.notes)
    implementation(projects.di.notes)

    implementation(projects.di.utils)
    implementation(projects.core.resources)
}