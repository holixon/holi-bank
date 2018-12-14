import org.gradle.kotlin.dsl.embeddedKotlinVersion
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

object Versions {
  //val kotlin = "1.3.11"
  val kotlin = embeddedKotlinVersion
  val springBoot = "2.1.1.RELEASE"
  val springBootDependencyManagement = "1.0.6.RELEASE"
  val axon = "4.0.3"

  object Test {
    val jgiven = "0.17.0"
    val archunit = "0.9.3"
  }
}

object Dependencies {
  fun axon(name: String) = "org.axonframework:axon-$name:${Versions.axon}"
  fun jgiven(name:String) = "com.tngtech.jgiven:jgiven-$name:${Versions.Test.jgiven}"
  fun archunit(name:String) = "com.tngtech.archunit:archunit-$name:${Versions.Test.archunit}"
}
