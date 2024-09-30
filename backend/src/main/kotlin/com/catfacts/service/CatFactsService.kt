package com.catfacts.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import java.time.Duration

data class CatFact(
	val text: String,
	val status: Status
)

data class Status(
	val verified: Boolean? = null,
	val sentCount: Int? = null
)
data class RandomUserResponse(val results: List<RandomUser>)
data class RandomUser(val name: Name)
data class Name(val first: String, val last: String)

@Service
class CatFactsService(@Value("\${catfacts.api.url}") private val catFactsUrl: String,
					  @Value("\${randomuser.api.url}") private val randomUserUrl: String,
					  private val webClient: WebClient)
	{
		fun fetchRandomCatFact(): Mono<String> {
			return webClient.get()
				.uri(catFactsUrl)
				.retrieve()
				.bodyToMono(CatFact::class.java)
				.flatMap { fact ->
					if (isFactVerified(fact)) {
						Mono.just(fact.text)
					} else {
						fetchRandomCatFact()
					}
				}
				.onErrorResume { throwable ->
					when (throwable) {
						is WebClientResponseException -> {
							Mono.just("No fact available (Error: ${throwable.statusCode})")
						}
						else -> {
							Mono.just("No fact available (Error: ${throwable.message})")
						}
					}
				}
		}

		private fun isFactVerified(fact: CatFact): Boolean {
			return fact.status.verified == true
		}


		fun fetchRandomUser(): Mono<String> {
		return webClient.get()
			.uri(randomUserUrl)
			.retrieve()
			.bodyToMono(RandomUserResponse::class.java)
			.map { randomUserResponse ->
				val randomUser = randomUserResponse.results.firstOrNull()
				val name = randomUser?.name
				"${name?.first ?: "Unknown"} ${name?.last ?: "User"}"
			}
			.onErrorResume { throwable ->
				when (throwable) {
					is WebClientResponseException -> {
						Mono.just("Unknown User (Error: ${throwable.statusCode})")
					}
					else -> {
						Mono.just("Unknown User (Error: ${throwable.message})")
					}
				}
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