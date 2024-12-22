plugins {
    alias(libs.plugins.astracrypt.android.library)
    alias(libs.plugins.astracrypt.android.library.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.nevidimka655.notes"
}

dependencies {
    api(projects.domain.notes)

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