import org.gradle.kotlin.dsl.embeddedKotlinVersion
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

object Versions {
  val kotlin = embeddedKotlinVersion
  val springBoot = "2.1.1.RELEASE"
  val axon = "4.0.3"

  @Deprecated("with gradle 5 we do not need the plugin")
  val springBootDependencyManagement = "1.0.6.RELEASE"

  object Test {
    val jgiven = "0.17.0"
    val archunit = "0.9.3"
  }
}
