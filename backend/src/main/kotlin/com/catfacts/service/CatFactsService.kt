package com.catfacts.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import java.time.Duration

data class CatFact(val text: String)
data class RandomUserResponse(val results: List<RandomUser>)
data class RandomUser(val name: Name)
data class Name(val first: String, val last: String)

@Service
class CatFactsService(private val webClient: WebClient) {

	fun fetchRandomCatFact(): Mono<String> {
		return webClient.get()
			.uri("https://cat-fact.herokuapp.com/facts/random")
			.retrieve()
			.bodyToMono(CatFact::class.java)
			.map { it.text }
	}

	fun fetchRandomUser(): Mono<String> {
		return webClient.get()
			.uri("https://randomuser.me/api/")
			.retrieve()
			.bodyToMono(RandomUserResponse::class.java)
			.map { randomUserResponse ->
				val randomUser = randomUserResponse.results.firstOrNull()
				val name = randomUser?.name
				"${name?.first ?: "Unknown"} ${name?.last ?: "User"}"
			}
	}

	fun getCatFactsStream(): Flux<Map<String, String>> {
		return Flux.interval(Duration.ofSeconds(10))
			.flatMap {
				Mono.zip(fetchRandomCatFact(), fetchRandomUser())
					.map { tuple: Tuple2<String, String> ->
						val fact = tuple.t1
						val user = tuple.t2
						mapOf("user" to user, "fact" to fact)
					}
			}
	}
}