package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication() : CommandLineRunner {

    override fun run(vararg args: String?) {
        print("Hello World!")
    }

}

fun main(args: Array<String>) = runApplication<DemoApplication>(*args).let { Unit }
