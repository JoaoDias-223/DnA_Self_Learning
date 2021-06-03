package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonRepositoryController
import com.slearning.pokedex.controller.Serializer.toJson
import com.slearning.pokedex.model.dtos.PokemonDTO
import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
@RestController
class URLController (private val pokemonRepositoryController: PokemonRepositoryController){

	@GetMapping("/list_of_pokemon")
	fun getAllPokemons(response: HttpServletResponse) : String {
		response.status = OK.value()
		response.contentType = "application/json"

		return pokemonRepositoryController.getPokemons().toJson()
	}

	/* Trocar o header Description por uma mensagem no body*/
	@PostMapping("/pokemon")
	fun createPokemon(@RequestBody body: PokemonDTO, response: HttpServletResponse) : String {
//		response.contentType = "application/json"
//
//		return when (pokemonRepositoryController.createPokemon(body)) {
//			PokemonRepositoryController.DUPLICATE_POKEMON_ERROR -> {
//				response.status = BAD_REQUEST.value()
//				"Pokemon's already registered"
//			}
//
//			PokemonRepositoryController.UNREGISTERED_SKILL_ERROR -> {
//				response.status = BAD_REQUEST.value()
//				"Pokemon's skill not registered"
//			}
//
//			PokemonRepositoryController.TYPE_AND_SKILL_MISMATCH -> {
//				response.status = BAD_REQUEST.value()
//				"Pokemon's types and skills mismatching"
//			}
//
//			else -> {
//				response.status = OK.value()
//				"Everything's fine"
//			}
//		}
		return "OK"
	}

	@GetMapping("/list_of_pokemon/{pokemonID}")
	fun getPokemon(@PathVariable pokemonID: Long, response: HttpServletResponse) : String {
		response.contentType = "application/json"
		return when(val pokemon =  pokemonRepositoryController.getPokemonById(pokemonID)) {
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
