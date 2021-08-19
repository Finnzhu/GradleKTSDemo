// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    ext{
        kotlin_version = "1.3.72"
        core_ktx_version = "1.6.0"

        minSdk = 21
        targetSdk = 30
        verCode = 100     // XYZ
        verName = "1.0.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    }

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:4.1.2"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

apply from: "signing.gradle"