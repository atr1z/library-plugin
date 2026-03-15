package mx.com.atriz

import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryUi : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginManager.apply(Library::class.java)

        project.library().apply {
            buildFeatures {
                viewBinding = true
            }
        }
    }
}
