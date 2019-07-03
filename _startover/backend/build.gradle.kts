import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


subprojects {


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

