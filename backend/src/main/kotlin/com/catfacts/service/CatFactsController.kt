package com.catfacts.service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/")
class CatFactController(private val catFactsService: CatFactsService) {

    @GetMapping()
    fun home(): String {
        return "Welcome to the Cat Facts API!"
    }

    @GetMapping("/facts")
    fun getCatFact(): Mono<String> {
        return catFactsService.fetchRandomCatFact()
    }

    @GetMapping("/random-user")
    fun getRandomUser():  Mono<String>{
        return catFactsService.fetchRandomUser()
    }

}
