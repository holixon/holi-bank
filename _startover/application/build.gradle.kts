import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  build.`kotlinSpringBootApplication`
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
}


  //compile("com.fasterxml.jackson.module:jackson-module-kotlin")
  //implementation("org.springframework.boot:spring-boot-starter-webflux")
//
//  implementation(":backend:graphql")
//  compile("com.graphql-java-kickstart:graphql-spring-boot-starter:5.9.0")
//
//  // to embed Altair tool
//  runtime("com.graphql-java-kickstart:altair-spring-boot-starter:5.9.0")
//
//  // to embed GraphiQL tool
//  runtime("com.graphql-java-kickstart:graphiql-spring-boot-starter:5.9.0")
//
//  // to embed Voyager tool
//  runtime("com.graphql-java-kickstart:voyager-spring-boot-starter:5.9.0")
//
//  // testing facilities
//  // testCompile 'com.graphql-java-kickstart:graphql-spring-boot-starter-test:5.9.0'
////  implementation(project(":backend:rest"))
////
////  compile("org.springframework.boot:spring-boot-starter-webflux")
////  compile("org.springframework.boot:spring-boot-starter-data-jpa")
////
////  compile(Dependencies.axon("spring-boot-starter")) {
////    exclude( module = "axon-server-connector")
////  }
////  compile("com.h2database:h2")
