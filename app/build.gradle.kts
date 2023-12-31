import com.android.build.api.dsl.Packaging

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.santansarah.perrydemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.santansarah.perrydemo"
        minSdk = 29
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("LICENSE.txt")
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    androidTestImplementation(libs.bundles.junit5)
    implementation(libs.androidx.compose.runtime.rxjava3)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.startup.runtime)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.rxjava3)
    implementation(libs.rxjava3.android)
    implementation(project(":domainmodels"))
    implementation(project(":viewmodels"))
    implementation(project(":feature"))
    implementation(project(":interfaces"))
    implementation(project(":networklogic"))
    implementation(project(":repositories"))
    implementation(libs.bundles.junit5)
}