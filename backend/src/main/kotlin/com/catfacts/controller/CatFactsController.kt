package com.catfacts.controller

import com.catfacts.service.CatFactsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/")
class CatFactController(private val catFactsService: CatFactsService) {

    @GetMapping()
    fun home(): String {
        return "Welcome to the Cat Facts API!"
    }

    @CrossOrigin(origins = ["http://localhost:5173"])
    @GetMapping("/cat-facts", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getCatFactsWithUser():  Flux<Map<String, String>> {
        return catFactsService.getCatFactsStream()
    }
}