package com.slearning.pokedex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*

@SpringBootApplication (exclude = [DataSourceAutoConfiguration::class])
@RestController
class URLController : Routes{
	override fun getAllPokemons() : String {
		TODO("Not yet implemented")
	}

	override fun createPokemon(name: String, type: Array<String>) : String {
		TODO("Not yet implemented")
	}

	override fun getPokemon(id: Int) : String {
		TODO("Not yet implemented")
	}

	override fun deletePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}

	override fun updatePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}

}



fun main(args: Array<String>) {
	runApplication<URLController>(*args) // *: spread operator (same as ... in JS)
}

// Annotations @: metadata.