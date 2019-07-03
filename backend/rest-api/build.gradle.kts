import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.hidetake.swagger.generator") version "2.18.1"
}

sourceSets {
  main {
    java.srcDir("build/swagger/src/main")
  }
}


dependencies {
  swaggerCodegen("io.swagger:swagger-codegen-cli:2.4.5")
}



configure {
  swaggerSources {
    create("createDTOs") {
      setInputFile(file("./src/main/resources/swagger.yaml"))
      code.outputDir = file("$buildDir/swagger")
      code.language = "kotlin-server"
      //code.templateDir = file("src/main/swagger-templates")
      code.components = setOf("models")
      //code.configFile = file("swagger-config.json")
      code.additionalProperties = mapOf("modelPackage" to "de.holisticon.holibank.backend.api.rest")
    }
  }
}

tasks {
  withType<KotlinCompile> {
    dependsOn("generateSwaggerCode")
  }
}
