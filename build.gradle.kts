
plugins {
  base
  idea
}

allprojects {
  group = "de.holisticon.bank"
  version = "0.1.0-SNAPSHOT"

  apply {
    from("${rootProject.rootDir}/gradle/repositories.gradle.kts")
  }
}


dependencies {
  subprojects.forEach {
    archives(it)
  }
}

