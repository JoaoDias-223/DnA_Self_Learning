package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonRepository
import com.slearning.pokedex.model.dtos.PokemonDTO
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import com.slearning.pokedex.controller.Serializer
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
	fun createPokemon(@RequestBody body: PokemonDTO, response: HttpServletResponse) : PokemonDTO {
		when (pokemonController.createPokemon(body)) {

			PokemonRepository.DUPLICATE_POKEMON_ERROR -> {
				println("-----------DUPLICATE POKEMON ERROR-----------")
				response.status = BAD_REQUEST.value(); response.addHeader("Description", "Pokemon's already registered")
			}

			PokemonRepository.UNREGISTERED_SKILL_ERROR -> {
				println("-----------UNREGISTERED SKILL ERROR-----------")
				response.status = BAD_REQUEST.value(); response.addHeader("Description", "Pokemon's skill not registered")
			}

			PokemonRepository.TYPE_AND_SKILL_MISMATCH -> {
				println("-----------TYPE AND SKILL MISMATCH-----------")
				response.status = BAD_REQUEST.value(); response.addHeader("Description", "Pokemon's types and skills mismatching")
			}

			PokemonRepository.OK -> {
				println("-----------OK-----------")
				response.status = OK.value(); response.addHeader("Description", "Everything's fine")
			}
		}

		response.contentType = "application/json"

		return body
	}

	@GetMapping("/list_of_pokemon/{pokemonID}")
	fun getPokemon(@RequestParam(name = "id", required = true)id: Int) : String {
		TODO("Not yet implemented")
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
