package de.holisticon.holibank.application

import org.springframework.boot.WebApplicationType
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.webmvc.webMvc
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse.ok

/**
 * Runs the application
 */
fun main() = application(WebApplicationType.SERVLET) {
  beans {
    bean<SampleService>()
    bean<SampleHandler>()
    configurationProperties<HoliBankProperties>(prefix= "holi-bank")
  }
  webMvc {
    port = if (profiles.contains("test")) 8181 else 8080
    router {
      val handler = ref<SampleHandler>()
      GET("/", handler::hello)
      GET("/api", handler::json)
    }
    converters {
      string()
      jackson()
    }
  }
}.run().let { Unit }

//= runApplication<HoliBankApplication>(*args).let { Unit }

data class Sample(val message: String)

data class HoliBankProperties(
  val name:String
)

class SampleService(val props: HoliBankProperties) {
  fun generateMessage() = "Hello ${props.name}!"
}

@Suppress("UNUSED_PARAMETER")
class SampleHandler(private val sampleService: SampleService) {
  fun hello(request: ServerRequest) = ok().body(sampleService.generateMessage())
  fun json(request: ServerRequest) = ok().body(Sample(sampleService.generateMessage()))
}
