@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    id("java-test-fixtures")
    id("com.google.devtools.ksp")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
}


dependencies {
    implementation(libs.koin.core)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    implementation(libs.rxjava3)
    implementation(project(":domainmodels"))
    ksp(libs.moshi.codegen)
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.kluent)
    testImplementation(libs.mockk)
    testFixturesImplementation(libs.koin.core)
    testFixturesImplementation(libs.rxjava3)
    testFixturesImplementation(project(":domainmodels"))
    testFixturesImplementation(project(":interfaces"))
}


