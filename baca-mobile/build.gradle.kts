plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
}

kotlin {
    androidTarget()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.core)
            implementation(libs.ktor.client.core)
        }
    }
}

android {
    namespace = "id.skuy.titeny.baca"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
