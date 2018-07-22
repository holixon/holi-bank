import de.holisticon.bank.build.Versions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    base
    application
    idea

    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.kotlin.plugin.spring") version Versions.kotlin

    id("org.springframework.boot") version Versions.springBootVersion

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
