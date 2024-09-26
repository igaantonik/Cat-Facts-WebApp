package com.catfacts.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

data class CatFact(val text: String)
data class RandomUserResponse(val results: List<RandomUser>)
data class RandomUser(val name: Name)
data class Name(val first: String, val last: String)
data class FactWithUser(val user:String, val fact: String)

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

	fun getCatFactsStream(): Flux<List<FactWithUser>> {
		val catFactList = mutableListOf<FactWithUser>()

		return Flux.interval(Duration.ofSeconds(10))
			.flatMap {
				Mono.zip(fetchRandomCatFact(), fetchRandomUser())
					.map { tuple ->
						val fact = tuple.t1
						val user = tuple.t2
						val factWithUser = FactWithUser(user, fact)
						catFactList.add(factWithUser)
						catFactList.toList()
					}
			}
	}
}