plugins {
  id("com.eden.orchidPlugin") version Versions.orchid
}

dependencies {
  fun orchid(module: String) = "io.github.javaeden.orchid:Orchid$module:${Versions.orchid}"

  orchidRuntime(orchid("BsDoc"))
  orchidRuntime(orchid("Kotlindoc"))
  orchidRuntime(orchid("PluginDocs"))
  orchidRuntime(orchid("Wiki"))
  orchidRuntime(orchid("Pages"))
  orchidRuntime(orchid("Changelog"))
}


orchid {
  theme = "BsDoc"
  baseUrl = "https://holisticon.github.io/holi-bank"
  version = "0.1.0-SNAPSHOT"
}
