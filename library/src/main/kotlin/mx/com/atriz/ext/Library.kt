package mx.com.atriz.ext

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

fun Project.library(): LibraryExtension = extensions.getByType(LibraryExtension::class.java)
