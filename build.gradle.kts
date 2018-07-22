import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    val kotlinVersion = "1.2.51"
    val springBootVersion = "2.0.3.RELEASE"

    base
    application
    idea

    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion

    id("org.springframework.boot") version springBootVersion

    //id("org.springframework.boot")
    //id("io.spring.dependency-management")

    id("io.spring.dependency-management") version "1.0.5.RELEASE"
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
