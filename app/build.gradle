plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

android {
    compileSdkVersion 30
    buildToolsVersion "30.0.2"

    defaultConfig {
        applicationId "com.android.gradlekts"
        minSdkVersion minSdk
        targetSdkVersion targetSdk
        versionCode verCode
        versionName verName

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        release {
            keyAlias KeyAlias
            keyPassword KeyPassword
            storeFile file(StoreFile)
            storePassword StorePassword
            v1SigningEnabled true
            v2SigningEnabled true
        }
    }


    buildTypes {
        all {
            buildConfigField("String", "API_URL", "\"https://api.github.com/\"")
        }
        debug {
            minifyEnabled false
            testCoverageEnabled project.hasProperty("coverage")
            debuggable true
            applicationIdSuffix ".dev"
            versionNameSuffix "-DEV"
            signingConfig signingConfigs.debug
        }

        release {
            minifyEnabled false
            shrinkResources false
            debuggable false
            proguardFiles getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            signingConfig signingConfigs.release
        }
    }

    flavorDimensions "default"
    productFlavors {
        google {
        }
        other {
        }
    }
    productFlavors.all {
        flavor -> flavor.manifestPlaceholders = [CHANNEL_VALUE: name]
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {

    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation "androidx.core:core-ktx:$core_ktx_version"
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}