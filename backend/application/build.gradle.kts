plugins {
  kotlin("plugin.spring") version Versions.kotlin
  id("org.springframework.boot") version Versions.springBoot
  id("io.spring.dependency-management") version Versions.springBootDependencyManagement
}

dependencies {
  compile("org.springframework.boot:spring-boot-starter-webflux")
  compile("org.springframework.boot:spring-boot-starter-data-jpa")
  compile(Dependencies.axon("spring-boot-starter")) {
    exclude( module = "axon-server-connector")
  }
  compile("com.h2database:h2")

  compile("com.fasterxml.jackson.module:jackson-module-kotlin")



}


repositories {
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}


val test by tasks.getting(Test::class) {
  useJUnitPlatform()
}


