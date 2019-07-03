import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version Versions.kotlin
  id("org.springframework.boot") version Versions.springBoot
}


dependencies {
  implementation(
    platform("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}")
  )


  implementation("org.springframework.fu:spring-fu-kofu:${Versions.springFu}")
  implementation("org.springframework.boot:spring-boot-starter-web")

}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
  }
}

