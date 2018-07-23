import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    base
    idea

    kotlin("jvm") version Versions.kotlin
    kotlin ("plugin.spring") version Versions.kotlin
    id("org.springframework.boot") version Versions.springBoot
    id("io.spring.dependency-management") version Versions.springBootDependencyManagement
}


group = "de.holisticon.bank"
version = "1.0.0-SNAPSHOT"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}


dependencies {
    compile(kotlin("stdlib"))

    compile("org.springframework.boot:spring-boot-starter")

}

repositories {
    jcenter()
}
