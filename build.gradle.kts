import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.SonatypeHost

group = "mx.com.atriz"
version = providers.exec {
    commandLine("git", "describe", "--tags", "--abbrev=0")
    isIgnoreExitValue = true
}.standardOutput.asText.map { it.trim().removePrefix("v") }
    .map { if (it.isEmpty()) "0.0.0-SNAPSHOT" else it }
    .get()

plugins {
    signing
    kotlin("jvm") version "2.0.20"
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.3.1"
    id("com.vanniktech.maven.publish") version "0.30.0"
}

repositories {
    mavenCentral()
    mavenLocal()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:8.8.1")
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.0.20")
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
            version = project.version
            displayName = "Atriz Library Plugin"
            description = "Base Android library module configuration"
            tags = listOf("atriz", "library-plugin", "android")
        }
        create("libraryUi") {
            id = "mx.com.atriz.library.ui"
            implementationClass = "mx.com.atriz.LibraryUi"
            version = project.version
            displayName = "Atriz Library UI Plugin"
            description = "Android library module configuration with UI support (View Binding)"
            tags = listOf("atriz", "library-plugin", "android", "ui", "viewbinding")
        }
    }
}

mavenPublishing {
    configure(GradlePublishPlugin())
    pom {
        name.set("Library Plugin")
        description.set("Library module settings ready to build Android's libraries or multi module apps")
        inceptionYear.set("2024")
        url.set("https://github.com/atr1z/library-plugin/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("atr1z")
                name.set("Atriz")
                url.set("https://github.com/atr1z/")
            }
        }
        scm {
            url.set("https://github.com/atr1z/library-plugin/")
            connection.set("scm:git:git://github.com/atr1z/library-plugin.git")
            developerConnection.set("scm:git:ssh://git@github.com/atr1z/library-plugin.git")
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()
}

val signingKey = System.getenv("SIGNING_KEY")

signing {
    if (!signingKey.isNullOrBlank()) {
        useInMemoryPgpKeys(signingKey, System.getenv("SIGNING_PASSWORD") ?: "")
        sign(configurations.runtimeElements.get())
    }
}
