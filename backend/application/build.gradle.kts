plugins {
  kotlin("jvm")
  kotlin("plugin.spring") version Versions.kotlin
  id("org.springframework.boot") version Versions.springBoot
  id("io.spring.dependency-management") version Versions.springBootDependencyManagement
}

dependencies {
  compile(project(":backend:domain"))

  compile("org.springframework.boot:spring-boot-starter-webflux")
  compile("org.springframework.boot:spring-boot-starter-data-jpa")
  compile(Dependencies.axon("spring-boot-starter")) {
    exclude( module = "axon-server-connector")
  }
  compile("com.h2database:h2")

  compile("com.fasterxml.jackson.module:jackson-module-kotlin")



// TODO: centralize junit/spring test config
  testCompile("org.springframework.boot:spring-boot-starter-test") {
    exclude(module = "junit")
  }
  testImplementation(Dependencies.jupiter("api"))
  testRuntimeOnly(Dependencies.jupiter("engine"))

  testCompile(Dependencies.axon("test"))
  testCompile("io.projectreactor:reactor-test")

  testCompile(Dependencies.jgiven("junit"))
  testCompile(Dependencies.jgiven("spring"))
  testCompile(Dependencies.jgiven("html5-report"))

  testCompile(Dependencies.archunit("junit5-api"))
  testCompile(Dependencies.archunit("junit5-engine"))

}


repositories {
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}




