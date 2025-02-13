plugins {
    kotlin("jvm") version "2.0.21"
    `kotlin-dsl`
    signing
    id("eu.kakde.gradle.sonatype-maven-central-publisher") version "1.0.6"
}

group = "mx.com.atriz"

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

object Meta {
    val COMPONENT_TYPE = "java"
    val GROUP = "mx.com.atriz"
    val ARTIFACT_ID = "library"
    val VERSION = "0.0.7"
    val PUBLISHING_TYPE = "AUTOMATIC"
    val SHA_ALGORITHMS = listOf("SHA-256", "SHA-512")
    val DESC = "Module library for android applications"
    val LICENSE = "Apache-2.0"
    val LICENSE_URL = "https://opensource.org/licenses/Apache-2.0"
    val GITHUB_REPO = "atr1z/library-plugin.git"
    val DEVELOPER_ID = "atr1z"
    val DEVELOPER_NAME = "Atriz"
    val DEVELOPER_ORGANIZATION = "Atriz"
    val DEVELOPER_ORGANIZATION_URL = "https://atriz.com.mx"
}

sonatypeCentralPublishExtension {
    groupId.set(Meta.GROUP)
    artifactId.set(Meta.ARTIFACT_ID)
    version.set(Meta.VERSION)
    componentType.set(Meta.COMPONENT_TYPE)
    publishingType.set(Meta.PUBLISHING_TYPE)
    username.set(System.getenv("SONATYPE_USERNAME") ?: "")
    password.set(System.getenv("SONATYPE_PASSWORD") ?: "")
    pom {
        name.set(Meta.ARTIFACT_ID)
        description.set(Meta.DESC)
        url.set("https://github.com/${Meta.GITHUB_REPO}")
        licenses {
            license {
                name.set(Meta.LICENSE)
                url.set(Meta.LICENSE_URL)
            }
        }
        developers {
            developer {
                id.set(Meta.DEVELOPER_ID)
                name.set(Meta.DEVELOPER_NAME)
                organization.set(Meta.DEVELOPER_ORGANIZATION)
                organizationUrl.set(Meta.DEVELOPER_ORGANIZATION_URL)
            }
        }
        scm {
            url.set("https://github.com/${Meta.GITHUB_REPO}")
            connection.set("scm:git:https://github.com/${Meta.GITHUB_REPO}")
            developerConnection.set("scm:git:https://github.com/${Meta.GITHUB_REPO}")
        }
        issueManagement {
            system.set("GitHub")
            url.set("https://github.com/${Meta.GITHUB_REPO}/issues")
        }
    }
}
signing {
    useInMemoryPgpKeys(
        System.getenv("SIGNING_KEY") ?: "",
        System.getenv("SIGNING_PASSWORD") ?: ""
    )
}