package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonRepository
import com.slearning.pokedex.model.dtos.PokemonDTO
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import com.slearning.pokedex.controller.Serializer.toJson

@SpringBootApplication (exclude = [DataSourceAutoConfiguration::class])
@RestController
class URLController (private val pokemonController: PokemonRepository){

	@GetMapping("/list_of_pokemon")
	fun getAllPokemons(response: HttpServletResponse) : String {
		response.status = OK.value()
		response.contentType = "application/json"

		return pokemonController.getPokemons().toJson()
	}

	/* Trocar o header Description por uma mensagem no body*/
	@PostMapping("/pokemon")
	fun createPokemon(@RequestBody body: PokemonDTO, response: HttpServletResponse) : String {
		response.contentType = "application/json"

		return when (pokemonController.createPokemon(body)) {
			PokemonRepository.DUPLICATE_POKEMON_ERROR -> {
				response.status = BAD_REQUEST.value()
				"Pokemon's already registered"
			}

			PokemonRepository.UNREGISTERED_SKILL_ERROR -> {
				response.status = BAD_REQUEST.value()
				"Pokemon's skill not registered"
			}

			PokemonRepository.TYPE_AND_SKILL_MISMATCH -> {
				response.status = BAD_REQUEST.value()
				"Pokemon's types and skills mismatching"
			}

			else -> {
				response.status = OK.value()
				"Everything's fine"
			}
		}
	}

	@GetMapping("/list_of_pokemon/{pokemonID}")
	fun getPokemon(@PathVariable pokemonID: String, response: HttpServletResponse) : String {
		response.contentType = "application/json"
		return when(val pokemon =  pokemonController.getPokemonById(pokemonID)) {
			null -> {
				response.status = NOT_FOUND.value()
				"{}"
			}
			else -> {
				response.status = OK.value()
				pokemon.toString()
			}
		}
	}

	@DeleteMapping("/list_of_pokemon/{pokemonID}")
	fun deletePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}

	@PutMapping("/list_of_pokemon/update")
	fun updatePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}
}

fun main(args: Array<String>) {
	runApplication<URLController>(*args) // *: spread operator (same as ... in JS)
}
