plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.kocelainterview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kocelainterview"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //implementation(libs.compose.preview.renderer)
    //implementation(libs.androidx.material3.android)
    //implementation(libs.androidx.paging.compose)
    // implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //navigation
    implementation("androidx.navigation:navigation-compose:2.7.2")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    //coroutine Lifecycle Scope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    //Dagger - hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-compiler:2.49")

    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")


    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.9.1")

    //client
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")

    //coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //full layout without status bar
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.1-alpha")

    //location
    implementation("com.google.android.gms:play-services-location:21.2.0")



    //Room database
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")


    testImplementation("junit:junit:4.13")
    testImplementation("org.mockito:mockito-core:5.7.0")
    androidTestImplementation("org.mockito:mockito-core:5.7.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")

    // Use mockito-android for Android instrumentation tests
    androidTestImplementation ("org.mockito:mockito-android:4.4.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")

    testImplementation("io.mockk:mockk:1.12.0")
    androidTestImplementation("io.mockk:mockk-android:1.12.0")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")





    //truth
    testImplementation ("com.google.truth:truth:1.0.1")





}
//allow references to generate code
kapt{
    correctErrorTypes = true
}