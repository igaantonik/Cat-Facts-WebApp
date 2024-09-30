package com.catfacts.controller

import com.catfacts.service.CatFactsService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

@WebFluxTest(controllers = [CatFactController::class])
class CatFactControllerTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var catFactsService: CatFactsService

    @Test
    fun `home should return welcome message`() {
        webTestClient.get().uri("/")
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java)
            .isEqualTo("Welcome to the Cat Facts API!")
    }

    @Test
    fun `getCatFactsWithUser should return a stream of cat facts and users`() {
        val factsStream = Flux.just(
            mapOf("user" to "user1", "fact" to "Cats are cool!"),
            mapOf("user" to "user2", "fact" to "Cats sleep a lot!")
        )

        given(catFactsService.getCatFactsStream()).willReturn(factsStream)

        webTestClient.get().uri("/cat-facts")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)  // Use contentTypeCompatibleWith
            .expectBodyList(Map::class.java)
            .hasSize(2)
            .contains(
                mapOf("user" to "user1", "fact" to "Cats are cool!"),
                mapOf("user" to "user2", "fact" to "Cats sleep a lot!")
            )
    }
}
