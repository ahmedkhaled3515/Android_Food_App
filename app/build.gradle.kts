plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.foodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    dependencies {
        // Import the BoM for the Firebase platform
        implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

        // Add the dependency for the Firebase Authentication library
        // When using the BoM, you don't specify versions in Firebase library dependencies
        implementation("com.google.firebase:firebase-auth")
    }
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-rxjava3:2.6.1")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")


    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")
    implementation ("com.airbnb.android:lottie:4.2.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}