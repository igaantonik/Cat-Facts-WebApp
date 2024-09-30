package com.catfacts.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class CatFactsServiceTests {

	private val catFactsUrl = "http://mocked-catfacts-url"
	private val randomUserUrl = "http://mocked-randomuser-url"

	private lateinit var service: CatFactsService

	private val webClient = Mockito.mock(WebClient::class.java)
	private val requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec::class.java)
	private val requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec::class.java)
	private val responseSpec = Mockito.mock(WebClient.ResponseSpec::class.java)

	@BeforeEach
	fun setup() {
		service = CatFactsService(catFactsUrl, randomUserUrl, webClient)

		given(webClient.get()).willReturn(requestHeadersUriSpec)
		given(requestHeadersUriSpec.uri(Mockito.anyString())).willReturn(requestHeadersSpec)
		given(requestHeadersSpec.retrieve()).willReturn(responseSpec)
	}

	@Test
	fun `fetchRandomCatFact should return a verified cat fact`() {
		val verifiedFact = CatFact(text = "Cats are cool!", status = Status(verified = true))

		given(responseSpec.bodyToMono(CatFact::class.java)).willReturn(Mono.just(verifiedFact))

		StepVerifier.create(service.fetchRandomCatFact())
			.expectNext("Cats are cool!")
			.verifyComplete()
	}

	@Test
	fun `fetchRandomUser should return a valid user`() {
		val randomUserResponse = RandomUserResponse(results = listOf(RandomUser(Name("Jake", "Rock"))))

		given(responseSpec.bodyToMono(RandomUserResponse::class.java)).willReturn(Mono.just(randomUserResponse))

		StepVerifier.create(service.fetchRandomUser())
			.expectNext("Jake Rock")
			.verifyComplete()
	}
}
