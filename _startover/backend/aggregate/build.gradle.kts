import _buildsrc.junit5

plugins {
  id("com.tngtech.jgiven.gradle-plugin") version Versions.Test.jgiven
  id("org.jetbrains.kotlin.plugin.allopen") version Versions.kotlin
}

dependencies {
  implementation(project(":backend:core-api"))
  implementation("org.axonframework:axon-configuration:${Versions.axon}")
  implementation("org.axonframework:axon-messaging:${Versions.axon}")

  testImplementation("org.axonframework:axon-test:${Versions.axon}")
  testImplementation(files("${rootProject.rootDir}/libs/axon-jgiven-0.1.0-SNAPSHOT.jar")) // TODO: use from repo when published

  testImplementation("com.tngtech.jgiven:jgiven-junit5:${Versions.Test.jgiven}")
  testImplementation("org.assertj:assertj-core:3.12.2")
  testImplementation(junit5("api"))
  testRuntimeOnly(junit5("engine"))
  testImplementation("org.hamcrest:hamcrest-core:2.1")
  implementation("ch.qos.logback:logback-classic:1.2.3")
}


tasks {
  test {
    useJUnitPlatform()
    finalizedBy("jgivenTestReport")
  }
}

allOpen {
  annotation("de.holisticon.ranked.backend.JGivenStage")
}
