plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.after_project.datastorejava.string"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.after_project.datastorejava.string"
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
    buildToolsVersion = "36.0.0"
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Preferences DataStore (SharedPreferences like APIs)
    // dependencies {
    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // optional - RxJava2 support
    implementation("androidx.datastore:datastore-preferences-rxjava2:1.1.7")

    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.1.7")
    // var annotationProcessor = libs.room.compiler
    // }

    // var room_version = "2.6.1"

    implementation("androidx.room:room-runtime:2.7.1")
    annotationProcessor("androidx.room:room-compiler:2.7.1")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:2.7.1")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:2.7.1")
}