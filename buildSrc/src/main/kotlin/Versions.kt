import org.gradle.kotlin.dsl.embeddedKotlinVersion

object Versions {
  val kotlin = embeddedKotlinVersion
  val springBoot =  "2.2.0.RELEASE"
  val springFu = "0.2"



  val axon = "4.2"
  val logback = "1.2.3"
  val orchid = "0.17.0" // TODO update 0.17.1
  val graphql = "5.0.2"


  object Test {
    val junit5 = "5.4.2"
    val jgiven = "0.18.2"
    val archunit = "0.10.2"
  }
}
