repositories {

  // prefer artifacts from local cache
  mavenLocal()

  // if not found, search on jcenter
  jcenter()

  maven(url = "https://kotlin.bintray.com/kotlinx/")
}
