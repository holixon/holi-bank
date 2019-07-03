rootProject.name = "holi-bank"
rootProject.buildFileName = "build.gradle.kts"

//include(":docs")

include("application")

//include("backend")

//include("backend:core-api")

//include(
//  "backend:rest-api",
//  "backend:aggregate",
//  "backend:projection",
//  "backend:graphql",
//  "backend:rest"
//)


pluginManagement {
  repositories {
    gradlePluginPortal()
    maven("https://repo.spring.io/milestone")
    //maven("https://repo.spring.io/snapshot")
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "org.springframework.boot") {
        useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
      }
    }
  }
}
