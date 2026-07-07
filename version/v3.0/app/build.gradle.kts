plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.prozer.studentportalpro"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.prozer.studentportalpro"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.activity)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.cardview)
    implementation(libs.recyclerview)
    implementation(libs.swiperefreshlayout)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.play.services.location)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
