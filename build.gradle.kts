buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}