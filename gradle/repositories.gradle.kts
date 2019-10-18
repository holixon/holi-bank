repositories {

  // prefer artifacts from local cache
  mavenLocal()
  mavenCentral()

  // if not found, search on jcenter
  jcenter()

  maven(url = "https://repo.spring.io/milestone")
 // maven(url = "https://kotlin.bintray.com/kotlinx/")
}
