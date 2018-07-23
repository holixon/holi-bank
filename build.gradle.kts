// define plugins base+idea
plugins {
    base
    idea
}

// set gav for project and repos
allprojects {
    group = "de.holisticon.bank"
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
}

dependencies {
    subprojects.forEach {
        archives(it)
    }
}
