import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.0.20"
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.2.1"
    id("com.vanniktech.maven.publish") version "0.28.0"
}

group = "mx.com.atriz"
version = "0.0.4"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:8.6.0")
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
        create("library") {
            id = "mx.com.atriz.library"
            implementationClass = "mx.com.atriz.Plugin"
            version = version
            displayName = "Atriz Module Plugin"
            description = "All needed setup for application development"
        }
    }
}

mavenPublishing {
    configure(GradlePublishPlugin())
    pom {
        name.set("Application Plugin")
        description.set("Application settings ready to build")
        inceptionYear.set("2024")
        url.set("https://github.com/atr1z/application-plugin/")
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
                name.set("Jair M.")
                url.set("https://github.com/atr1z/")
            }
        }
        scm {
            url.set("https://github.com/atr1z/library-plugin/")
            connection.set("scm:git:git://github.com/atr1z/library-plugin.git")
            developerConnection.set("scm:git:ssh://git@github.com/atr1z/library-plugin.git")
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}