plugins {
  kotlin("plugin.spring") version Versions.kotlin
  id("org.springframework.boot") version Versions.springBoot
  id("io.spring.dependency-management") version Versions.springBootDependencyManagement
}

dependencies {
  compile("org.springframework.boot:spring-boot-starter")
}
