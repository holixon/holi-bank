import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
}

dependencies {
  api("org.axonframework:axon-modelling:${Versions.axon}")
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
