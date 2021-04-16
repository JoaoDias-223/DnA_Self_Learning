package com.slearning.pokedex

import com.slearning.pokedex.controller.DatabaseConnectionInfo
import com.slearning.pokedex.controller.DatabaseController
import com.slearning.pokedex.controller.PokemonController
import com.slearning.pokedex.model.bodyTemplates.PokemonBodyTemplate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@SpringBootApplication (exclude = [DataSourceAutoConfiguration::class])
@RestController
class URLController{
	companion object{
		private const val listOfPokemon: String = "/list_of_pokemon"
		private const val createPokemon: String = "$listOfPokemon/create"
		private const val readPokemon: String = "$listOfPokemon/{pokemonID}"
		private const val updatePokemon: String = "$listOfPokemon/update"
		private const val deletePokemon: String = "$listOfPokemon/delete"
		private val pokemonDatabaseController: DatabaseController = DatabaseController(DatabaseConnectionInfo("./src/main/resources/pokemons.db", "", "", true))
		private val skillsDatabaseController: DatabaseController = DatabaseController(DatabaseConnectionInfo("./src/main/resources/skills.db", "", "", true))
	}

	@GetMapping(listOfPokemon)
	fun getAllPokemons() : String {
		TODO("Not yet implemented")
	}

	@PostMapping(createPokemon)
	fun createPokemon(@RequestBody body: PokemonBodyTemplate, response: HttpServletResponse) : PokemonBodyTemplate {
		when (PokemonController.createPokemon(body, pokemonDatabaseController, skillsDatabaseController)) {
			PokemonController.DUPLICATE_POKEMON_ERROR -> { response.status = BAD_REQUEST.value(); response.addHeader("Description", "Pokemon's already registered") }
			PokemonController.UNREGISTERED_SKILL_ERROR -> { response.status = BAD_REQUEST.value(); response.addHeader("Description", "Pokemon's skill not registered") }
			PokemonController.TYPE_AND_SKILL_MISMATCH -> { response.status = BAD_REQUEST.value(); response.addHeader("Description", "Pokemon's types and skills mismatching") }
			PokemonController.OK -> { response.status = OK.value(); response.addHeader("Description", "Everything's fine") }
		}

		response.contentType = "application/json"
		response.characterEncoding = "UTF-8"

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
