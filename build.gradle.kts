
plugins {
  base
  idea

  kotlin("jvm") version Versions.kotlin apply false
}

allprojects {
  group = "de.holisticon.bank"
  version = "0.1.0-SNAPSHOT"

  extra["kotlin.version"] = Versions.kotlin

  apply {
    from("${rootProject.rootDir}/gradle/repositories.gradle.kts")
  }
}


dependencies {
  subprojects.forEach {
    archives(it)
  }
}

