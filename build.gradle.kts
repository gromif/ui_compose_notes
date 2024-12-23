plugins {
    alias(libs.plugins.astracrypt.android.library)
    alias(libs.plugins.astracrypt.android.library.compose)
    alias(libs.plugins.astracrypt.android.hilt)
}

android {
    namespace = "com.nevidimka655.notes"
}

dependencies {
    api(projects.domain.notes)
    implementation(projects.data.notes)
    implementation(projects.di.notes)

    implementation(projects.di.core)

    // Room
    api(libs.room.runtime)
    api(libs.room.ktx)
    api(libs.room.paging)
    ksp(libs.room.compiler)
}