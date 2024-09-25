package com.catfacts.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

data class CatFact(val text: String)
data class RandomUser(val results: List<User>)
data class User(val name: Name)
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
			.bodyToMono(RandomUser::class.java) // Directly convert to RandomUser class
			.map { randomUser ->
				val user = randomUser.results.firstOrNull() // Get the first user from the results
				val name = user?.name // Access the name property
				"${name?.first ?: "Unknown"} ${name?.last ?: "User"}" // Concatenate first and last name
			}
	}

}
