package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonController
import com.slearning.pokedex.model.Pokemon
import com.slearning.pokedex.model.bodyTemplates.PokemonBody
import org.json.JSONObject
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*

@SpringBootApplication (exclude = [DataSourceAutoConfiguration::class])
@RestController
class URLController{
	companion object{
		private const val listOfPokemon: String = "/list_of_pokemon"
		private const val createPokemon: String = "$listOfPokemon/create"
		private const val readPokemon: String = "$listOfPokemon/{pokemonID}"
		private const val updatePokemon: String = "$listOfPokemon/update"
		private const val deletePokemon: String = "$listOfPokemon/delete"
	}

	@GetMapping(listOfPokemon)
	fun getAllPokemons() : String {
		TODO("Not yet implemented")
	}

	@PostMapping(createPokemon)
	fun createPokemon(@RequestBody body: PokemonBody) : PokemonBody {
		PokemonController.createPokemon(body)
		return body
	}

	@GetMapping(readPokemon)
	fun getPokemon(id: Int) : String {
		TODO("Not yet implemented")
	}

	@DeleteMapping(deletePokemon)
	fun deletePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}

	@PutMapping(updatePokemon)
	fun updatePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}
}

fun main(args: Array<String>) {
	runApplication<URLController>(*args) // *: spread operator (same as ... in JS)
}

// Annotations @: metadata.