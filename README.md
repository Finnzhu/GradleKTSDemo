# GradleKTSDemo

Kotlin DSL(also known KTS) will be preferred over Groovy for writing Gradle scripts because Kotlin is more readable and offers better compile-time checking and IDE support. 


 - KTS Advantage:
   1. Use Kotlin, and developers may be more familiar with this language and prefer.
   2. IDE supports better, auto-completion prompts, refactoring, imports, etc.
   3. Type safety: Kotlin is statically typed.
   4. No need to migrate all at once: the scripts of the two languages can coexist or call each other.


 - KTS Disadvantages
   1. At present, the construction speed of using KTS may be slower than using Groovy.(so migrating to KTS we should be considered with build performance in mind.)



<br></br>
<br></br>
We will look at the main difference between Groovy DSL and Kotlin DSL from a scripting:
1. Renamed to gradle.kts.

```settings.gradle``` -> ```settings.gradle.kts```


<br></br>
2. KTS always requires double quotes.
Groovy:
```
group 'com.acme'
dependencies {
    implementation 'com.acme:example:1.0'
}
```
To KTS:
```
group "com.acme"
dependencies {
    implementation "com.acme:example:1.0"
}
```


<br></br>
3. KTS always requires the assignment operator"=".

```
group = "com.acme"                          
```


<br></br>
4. KTS always requires the parentheses"()".

```
dependencies {
    implementation("com.acme:example:1.0")  
}
```

<br></br>
According to the above, we can try to do some similar changes:
Groovy:
```
defaultConfig {
    applicationId "com.android.testplaydemo"
    minSdkVersion 21
    targetSdkVersion 30
    versionCode 1
    versionName "1.0"
 
    testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
}
```
To KTS：
```
defaultConfig {
    applicationId = "com.android.testplaydemo"
    minSdkVersion(21)
    targetSdkVersion(30)
    versionCode = 1
    versionName = "1.0"
 
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}
```


<br></br>
5. Use the plugins{} block to configure plugins.
Groovy:
```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'dagger.hilt.android.plugin'
}
```
To KTS:
```
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}
```


<br></br>
6. Explicit and implicit buildTypes, In the Kotlin DSL, certain buildTypes, such as debug and release, are available implicitly. Other buildTypes must be created manually.
Groovy:
```
buildTypes
  debug {
    ...
  }
  release {
    ...
  }
  staging {
    ...
  }
```
To KTS:
```
buildTypes
  getByName("debug") {
    ...
  }
  getByName("release") {
    ...
  }
  create("staging") {
    ...
  }
```


<br></br>
7. Add buildSrc directory which contains build info.
```
ext {
    kotlin_version = "1.4.10"
    other_version = "2.2.0"
}
```
To KTS:
```
object Libs {
    const val KOTLIN_VERSION = "1.4.10"
    const val OTHER_VERSION = "2.2.0"
}
```
For more buildSrc directory, please check -> [Better Dependency Management Using buildSrc + Kotlin DSL](https://proandroiddev.com/better-dependencies-management-using-buildsrc-kotlin-dsl-eda31cdb81bf)


<br></br>
<br></br>
Reference:
  - Migrating build logic from Groovy to Kotlin -> https://docs.gradle.org/current/userguide/migrating_from_groovy_to_kotlin_dsl.html
  - For a more comprehensive migration guide, please refer to Gradle's documentation -> https://developer.android.com/studio/build/migrate-to-kts


