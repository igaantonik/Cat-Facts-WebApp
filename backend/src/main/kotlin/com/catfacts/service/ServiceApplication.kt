package com.catfacts.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceApplication

fun main(args: Array<String>) {
    println("Hello!")
    runApplication<ServiceApplication>(*args)
}
