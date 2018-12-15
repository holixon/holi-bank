import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("plugin.spring") version Versions.kotlin
}

dependencies {
  //compile(platform("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}"))

  compile(Dependencies.axon("modelling"))
}

// TODO centralize
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
}
