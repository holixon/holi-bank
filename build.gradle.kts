import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  val kotlinVersion = "1.2.51"
  id("org.springframework.boot") version "2.0.3.RELEASE"
  id("org.jetbrains.kotlin.jvm") version kotlinVersion
  id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
  id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
  id("org.jetbrains.kotlin.kapt") version kotlinVersion

  id("io.spring.dependency-management") version "1.0.5.RELEASE"
  idea
}

group = "de.holisticon.bank"
version = "1.0.0-SNAPSHOT"

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-Xjsr305=strict")
  }
}

val test by tasks.getting(Test::class) {
  useJUnitPlatform()
}


repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
  compile("org.springframework.boot:spring-boot-starter-webflux")
  compile("org.springframework.boot:spring-boot-starter-data-jpa")
  compile("org.axonframework:axon-spring-boot-starter:3.3.2")
  compile("com.h2database:h2")

  compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  compile("org.jetbrains.kotlin:kotlin-reflect")
  compile("com.fasterxml.jackson.module:jackson-module-kotlin")

  testCompile("org.springframework.boot:spring-boot-starter-test") {
    exclude(module = "junit")
  }
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  testCompile("org.axonframework:axon-test:3.3.2")
  testCompile("io.projectreactor:reactor-test")
  testCompile("io.projectreactor:reactor-test")

  testCompile("com.tngtech.jgiven:jgiven-junit:0.16.0")
  testCompile("com.tngtech.jgiven:jgiven-spring:0.16.0")
  testCompile("com.tngtech.jgiven:jgiven-html5-report:0.16.0")

  testCompile("com.tngtech.archunit:archunit-junit5-api:0.9.0-junit5-SNAPSHOT")
  testRuntime("com.tngtech.archunit:archunit-junit5-engine:0.9.0-junit5-SNAPSHOT")

}
