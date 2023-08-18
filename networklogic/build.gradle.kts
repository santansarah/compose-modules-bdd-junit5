@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.kotlin.reflect)
    implementation(libs.moshi)
    implementation(libs.rxjava3)
    implementation(libs.square.okhttp)
    implementation(project(":domainmodels"))
    implementation(project(":interfaces"))
    testImplementation(libs.koin.test)
}