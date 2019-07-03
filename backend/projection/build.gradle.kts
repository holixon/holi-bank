import _buildsrc.junit5

dependencies {
  implementation(project(":backend:core-api"))
  implementation("ch.qos.logback:logback-classic:${Versions.logback}")

  testImplementation("org.assertj:assertj-core:3.12.2")
  testImplementation(junit5("api"))
  testRuntimeOnly(junit5("engine"))
  testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
}
