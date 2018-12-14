import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version Versions.kotlin
}

subprojects {

  apply {
    plugin("kotlin")
  }

  dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))

    testCompile("org.springframework.boot:spring-boot-starter-test") {
      exclude(module = "junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testCompile(Dependencies.axon("test"))
    testCompile("io.projectreactor:reactor-test")

    testCompile(Dependencies.jgiven("junit"))
    testCompile(Dependencies.jgiven("spring"))
    testCompile(Dependencies.jgiven("html5-report"))

    testCompile(Dependencies.archunit("junit5-api"))
    testCompile(Dependencies.archunit("junit5-engine"))
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf("-Xjsr305=strict")
    }
  }
}
