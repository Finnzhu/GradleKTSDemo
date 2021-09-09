//import java.time.LocalDate

plugins {
    id("com.android.application")
    id("kotlin-android")
}

private object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
    const val STAGING = "staging"

    const val DEV = "dev"
    const val STG = "stg"
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"

    defaultConfig {
        applicationId = "com.android.gradlekts"
        minSdkVersion(AppConfig.minAndroidSdk)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode = AppConfig.verCode
        versionName = AppConfig.verName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_URL", "\"https://api.github.com/\"")
    }

    signingConfigs {
        create(BuildTypes.RELEASE) {
            keyAlias = Signing.KeyAlias
            keyPassword = Signing.KeyPassword
            storeFile = File(Signing.StoreFile)
            storePassword = Signing.StorePassword
            isV1SigningEnabled = true
            isV2SigningEnabled = true
        }
    }


    buildTypes {
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = false
            isTestCoverageEnabled = project.hasProperty("coverage")
            isDebuggable = true
            applicationIdSuffix = ".${BuildTypes.DEV}"
            versionNameSuffix = "-${BuildTypes.DEV.toUpperCase()}"
            signingConfig = signingConfigs.getByName(BuildTypes.DEBUG)
            buildConfigField("String", "BASE_URL", "\"https://comdev.example.com/\"")
        }

        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE)
            buildConfigField("String", "BASE_URL", "\"https://com.example.com/\"")
        }

        // if you want to add other buildType, that will do example:
        create(BuildTypes.STAGING) {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            applicationIdSuffix = ".${BuildTypes.STG}"
            versionNameSuffix = "-${BuildTypes.STG.toUpperCase()}"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(BuildTypes.RELEASE)

            buildConfigField("String", "BASE_URL", "\"https://comstg.example.com/\"")
        }
    }

    flavorDimensions("default")
    productFlavors {
        create("google")
        create("other")
    }
    productFlavors.all {
        manifestPlaceholders["CHANNEL_VALUE"] = name
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

//    testOptions {
//        animationsDisabled = true
//        unitTests {
//            isIncludeAndroidResources = true
//        }
//    }

//    lintOptions {
//        disable("UnusedResources", "other Test")
//        isCheckReleaseBuilds = false
//    }

    // here we can import java.time.LocalDate
//    var testDate = LocalDate.now()

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.coreKtx)
    // for example
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}