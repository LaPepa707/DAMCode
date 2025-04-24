plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization") version "2.0.21"
    id("com.google.gms.google-services") version "4.4.2"

    // Add the dependency for the Crashlytics Gradle plugin
    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.montilivi.projecteautentificacio"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.montilivi.projecteautentificacio"
        minSdk = 24
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {


    //Firebase FireStore
    implementation ("com.google.firebase:firebase-firestore-ktx")

    //Firebase DataStorage
    implementation ("com.google.firebase:firebase-storage-ktx")

//Firebase Remote config
    implementation ("com.google.firebase:firebase-config-ktx")

//Descàrregues d'imatges d'Internet
    implementation ("io.coil-kt:coil-compose:2.5.0")

    //ColorPicker
    implementation("com.github.skydoves:colorpicker-compose:1.1.2")

    //importante
    implementation("com.google.android.gms:play-services-auth:21.3.0")
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")

    //Navegació
    implementation("androidx.navigation:navigation-compose:2.8.5")
    //Biblioteca extesa d'icones
    implementation("androidx.compose.material:material-icons-extended")
    //DataStore
    implementation ("androidx.datastore:datastore-preferences:1.1.1")
    //Lifecycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    //Serialització
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //Scalar converter de Retrofit
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    //Gson converter de Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    //Convertidors moshi de retrofit
    implementation ("com.squareup.moshi:moshi:1.11.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")


    //Coil  (Per a carregar imatges d'internet
    implementation ("io.coil-kt:coil-compose:2.5.0")

    //Constraint layout per a Compose
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.0")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}