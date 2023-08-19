@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.santansarah.viewmodels"
    compileSdk = 33

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.rxjava3)
    implementation(project(":domainmodels"))
    implementation(project(":repositories"))
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.kluent)
    testImplementation(libs.koin.test)
    testImplementation(libs.mockk)
    testImplementation(project(":repositories"))
    testImplementation(testFixtures(project(":interfaces")))

}