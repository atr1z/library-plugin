import com.vanniktech.maven.publish.GradlePublishPlugin
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    signing
    kotlin("jvm") version "2.0.20"
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.3.1"
    id("com.vanniktech.maven.publish") version "0.30.0"
}

group = "mx.com.atriz"
version = "0.1.0"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:8.8.1")
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

signing {
    useInMemoryPgpKeys(
        System.getenv("SIGNING_KEY") ?: "",
        System.getenv("SIGNING_PASSWORD") ?: ""
    )
    sign(configurations.runtimeElements.get())
}