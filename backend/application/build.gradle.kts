plugins {
  kotlin("plugin.spring") version "1.2.71"
  id("org.springframework.boot") version "2.0.5.RELEASE"
  id("io.spring.dependency-management") version "1.0.6.RELEASE"
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


repositories {
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}


val test by tasks.getting(Test::class) {
  useJUnitPlatform()
}


