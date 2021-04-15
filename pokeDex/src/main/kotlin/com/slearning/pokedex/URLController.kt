package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonController
import com.slearning.pokedex.model.bodyTemplates.PokemonBodyTemplate
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
	fun createPokemon(@RequestBody body: PokemonBodyTemplate) : MutableMap<String, Any> {
		val response: MutableMap<String, Any> = mutableMapOf()
		response["header"] = mutableMapOf<String, String>()


		val operationReturnCode: Int = PokemonController.createPokemon(body)
		var statusCode: String = ""
		var statusLine: String = ""
		var description: String = ""

		when {
			operationReturnCode == PokemonController.DUPLICATE_POKEMON_ERROR -> { statusCode = "400"; statusLine = "Bad Request"; description = "Pokemon's already registered" }
			operationReturnCode == PokemonController.UNREGISTERED_SKILL_ERROR -> { statusCode = "400"; statusLine = "Bad Request"; description = "Pokemon's skill not registered" }
			operationReturnCode == PokemonController.TYPE_AND_SKILL_MISMATCH -> { statusCode = "400"; statusLine = "Bad Request"; description = "Pokemon's types and skills mismatching'" }
			operationReturnCode == PokemonController.OK -> { statusCode = "200"; statusLine = "OK"; description = "Everything's fine" }
		}

		(response["header"] as MutableMap<String, String>).put("Status-Code", statusCode)
		(response["header"] as MutableMap<String, String>).put("Status-Line", statusLine)
		(response["header"] as MutableMap<String, String>).put("Description", description)
		response["body"] = body

		return response
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
