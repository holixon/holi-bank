import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version Versions.kotlin
}

subprojects {

  apply {
    plugin("kotlin")
  }

  dependencies {
    "compile"(kotlin("stdlib-jdk8"))
    "compile"(kotlin("reflect"))
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf(
        "-XXLanguage:+InlineClasses",
        "-Xjsr305=strict"
      )
    }
  }
}

