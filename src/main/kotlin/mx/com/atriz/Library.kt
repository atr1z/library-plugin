package mx.com.atriz

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Library : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        setProjectConfig(project)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("com.android.library")
            plugin("kotlin-android")
        }
    }

    private fun setProjectConfig(project: Project) {
        project.library().apply {
            compileSdk = Version.COMPILE_SDK

            defaultConfig {
                minSdk = Version.MIN_SDK
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                release {
                    enableUnitTestCoverage = true
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            buildFeatures {
                buildConfig = true
                viewBinding = true
            }

            compileOptions {
                sourceCompatibility = Version.java()
                targetCompatibility = Version.java()
            }
        }
    }
}

fun Project.library(): LibraryExtension = extensions.getByType(LibraryExtension::class.java)
