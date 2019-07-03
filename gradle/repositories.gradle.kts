repositories {

  // prefer artifacts from local cache
  mavenLocal()

  // if not found, search on jcenter
  jcenter()

  maven(url = "https://repo.spring.io/milestone")
  maven(url = "https://kotlin.bintray.com/kotlinx/")
}
