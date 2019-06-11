// define plugins base+idea
plugins {
  base
  idea
}

// set gav for project and repos
allprojects {
  group = "de.holisticon.bank"
  version = "1.0.0-SNAPSHOT"

  apply {
    from("${rootProject.rootDir}/gradle/repositories.gradle.kts")
  }

}

dependencies {
  // Make the root project archives configuration depend on every sub-project
  subprojects.forEach {
    archives(it)
  }
}
