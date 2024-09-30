package mx.com.atriz


import com.android.build.api.dsl.LibraryExtension
import mx.com.atriz.core.Version
import mx.com.atriz.ext.library
import org.gradle.api.Plugin
import org.gradle.api.Project

class Plugin : Plugin<Project> {

    override fun apply(project: Project) {
        setProjectConfig(project)
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
