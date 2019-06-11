import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


dependencies {
  //compile(platform("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}"))

  compile(Dependencies.axon("modelling"))
}

// TODO centralize
tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
  }
}
