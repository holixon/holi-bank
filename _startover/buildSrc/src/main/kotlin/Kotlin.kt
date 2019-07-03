package buildSrc;

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependenciesSpec

// TODO: remove?
/**
 * Configures the current project as a Kotlin project by adding the Kotlin `stdlib` as a dependency.
 */
fun Project.kotlinProject() {

  apply {

  }

  dependencies {
    "compile"(kotlin("stdlib-jdk8"))
    "compile"(kotlin("reflect"))
  }

//  tasks.withType<KotlinCompile> {
//    kotlinOptions {
//      jvmTarget = "1.8"
//      freeCompilerArgs = listOf(
//        "-XXLanguage:+InlineClasses",
//        "-Xjsr305=strict"
//      )
//    }
//  }
}
