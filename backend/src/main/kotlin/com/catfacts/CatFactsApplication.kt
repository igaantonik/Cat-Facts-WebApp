package com.catfacts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatFactsApplication

fun main(args: Array<String>) {
    runApplication<CatFactsApplication>(*args)
}
