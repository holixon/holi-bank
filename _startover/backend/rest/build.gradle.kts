plugins {
  kotlin("plugin.spring") version Versions.kotlin
}

dependencies {
  api(project(":backend:core-api"))
  api(project(":backend:rest-api"))

  implementation(platform("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}"))

  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.axonframework:axon-messaging:${Versions.axon}")
  implementation("org.axonframework:axon-eventsourcing:${Versions.axon}")


  api("org.springframework.boot:spring-boot-starter-data-jpa")

  //implementation("org.axonframework:axon-configuration:${Versions.axon}")
}
