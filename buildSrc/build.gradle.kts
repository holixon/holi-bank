import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

apply {
  from("../gradle/repositories.gradle.kts")
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}


// TODO: does this work here?
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

