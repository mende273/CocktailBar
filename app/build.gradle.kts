plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.jumrukovski.cocktailbar"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    tasks.getByPath("preBuild").dependsOn("ktlintFormat")

    ktlint {
        this.android.set(true)
        this.ignoreFailures.set(false)
        this.reporters {
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "com.jumrukovski.cocktailbar"
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Bottom Navigation
    implementation("io.ak1:bubbletabbar:1.0.8")

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // LiveCycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")

    // Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // OkHttp3
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Koin Dependency Injection
    implementation("io.insert-koin:koin-android:3.5.0")
    testImplementation("io.insert-koin:koin-test:3.5.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.5.0")

    // Coil Image Loader
    implementation("io.coil-kt:coil:2.5.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-coroutines:2.1.0-alpha04")
    ksp("androidx.room:room-compiler:2.6.1")
}
