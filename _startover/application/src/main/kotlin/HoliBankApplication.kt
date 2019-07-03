package de.holisticon.holibank.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * The HoliBank spring boot application.
 */
@SpringBootApplication
class HoliBankApplication {
}

/**
 * Runs the application
 */
fun main(args: Array<String>) = runApplication<HoliBankApplication>(*args).let { Unit }
