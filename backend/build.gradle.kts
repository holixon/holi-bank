import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


subprojects {

  plugins {
    kotlin("jvm") version Version.kotlin
  }

  dependencies {
    "implementation"(kotlin("stdlib-jdk8"))
    "implementation"(kotlin("reflect"))
  }

  tasks {
    withType<KotlinCompile> {
      kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf(
          "-XXLanguage:+InlineClasses",
          "-Xjsr305=strict"
        )
      }
    }
  }
}

