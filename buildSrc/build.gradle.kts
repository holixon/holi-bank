import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

repositories {
  mavenLocal()
  jcenter()
}

allprojects {
  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf(
        "-XXLanguage:+InlineClasses",
        "-Xjsr305=strict"
      )
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}

