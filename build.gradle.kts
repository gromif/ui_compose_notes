plugins {
    alias(libs.plugins.astracrypt.android.library)
    alias(libs.plugins.astracrypt.android.library.compose)
    alias(libs.plugins.astracrypt.android.hilt)
    alias(libs.plugins.astracrypt.android.hilt.compose)
    alias(libs.plugins.astracrypt.android.room)
}

android {
    namespace = "com.nevidimka655.notes"
}

dependencies {
    implementation(projects.domain.notes)
    implementation(projects.data.notes)
    implementation(projects.di.notes)

    implementation(projects.di.core)
}