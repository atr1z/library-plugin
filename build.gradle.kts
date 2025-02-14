plugins {
    signing
    kotlin("jvm") version "2.0.21"
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.3.1"
}

group = "mx.com.atriz"
version = "0.0.1"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:8.8.0")
}

kotlin {
    jvmToolchain(21)
}

gradlePlugin {
    website = "https://atriz.com.mx"
    vcsUrl = "https://github.com/atr1z/library-plugin"
    plugins {
        create("library") {
            id = "mx.com.atriz.library"
            implementationClass = "mx.com.atriz.Library"
            displayName = "Atriz Library Plugin"
            version = project.version
            description = "Android module library ready to use with custom configurations"
            tags = listOf("atriz", "library-plugin", "android")
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("SIGNING_KEY") ?: "",
        System.getenv("SIGNING_PASSWORD") ?: ""
    )
    sign(configurations.runtimeElements.get())
}