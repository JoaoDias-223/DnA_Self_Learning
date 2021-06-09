package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonRepositoryController
import com.slearning.pokedex.controller.Serializer.toJson
import com.slearning.pokedex.controller.resources.PokemonRepositoryControllerCodes
import com.slearning.pokedex.model.Pokemon
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
@RestController
@RequestMapping("/pokemon")
class PokemonController (private val pokemonRepositoryController: PokemonRepositoryController){

	@GetMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
	fun getAllPokemons(response: HttpServletResponse) : String {
		response.status = HttpStatus.OK.value()

		return pokemonRepositoryController.getPokemons().toJson()
	}

	@GetMapping(value = ["/{pokemonID}"], produces = [MediaType.APPLICATION_JSON_VALUE])
	fun getPokemon(@PathVariable id: String, response: HttpServletResponse) : String {
		return when(val pokemon =  pokemonRepositoryController.getPokemonById(id)) {
			null -> {
				response.status = HttpStatus.NOT_FOUND.value()
				"{}"
			}
			else -> {
				response.status = HttpStatus.OK.value()
				pokemon.toJson<Pokemon>()
			}
		}
	}

	@PostMapping("/")
	fun createPokemon(@RequestBody body: Pokemon, response: HttpServletResponse) : String {
		return when(val result: PokemonRepositoryControllerCodes = pokemonRepositoryController.createPokemon(body)) {
			PokemonRepositoryControllerCodes.DUPLICATE_POKEMON_ERROR,
			PokemonRepositoryControllerCodes.UNREGISTERED_SKILL_ERROR,
			PokemonRepositoryControllerCodes.TYPE_AND_SKILL_MISMATCH ->
			{
				response.status = HttpStatus.BAD_REQUEST.value()
				result.name
			}

			PokemonRepositoryControllerCodes.OK -> {
				response.status = HttpStatus.OK.value()
				body.toJson<Pokemon>()
			}

			else -> {
				response.status = HttpStatus.BAD_REQUEST.value()
				PokemonRepositoryControllerCodes.UNKNOWN_ERROR.name
			}
		}
	}

	@PutMapping("/{pokemonID}")
	fun updatePokemon(
		@PathVariable id: String,
		@RequestBody body: Pokemon,
		response: HttpServletResponse
	) : String {
		return when(val result: PokemonRepositoryControllerCodes = pokemonRepositoryController.updatePokemonById(
			id, body
		)) {
			PokemonRepositoryControllerCodes.DUPLICATE_POKEMON_ERROR,
			PokemonRepositoryControllerCodes.UNREGISTERED_SKILL_ERROR,
			PokemonRepositoryControllerCodes.TYPE_AND_SKILL_MISMATCH ->
			{
				response.status = HttpStatus.BAD_REQUEST.value()
				result.name
			}

			PokemonRepositoryControllerCodes.OK -> {
				response.status = HttpStatus.OK.value()
				body.toJson<Pokemon>()
			}

			else -> {
				response.status = HttpStatus.BAD_REQUEST.value()
				PokemonRepositoryControllerCodes.UNKNOWN_ERROR.name
			}
		}
	}

	@DeleteMapping("/{pokemonID}")
	fun deletePokemon(@PathVariable id: String, response: HttpServletResponse) : String {
		return when(val result: PokemonRepositoryControllerCodes = pokemonRepositoryController.deletePokemonById(id)) {
			PokemonRepositoryControllerCodes.UNREGISTERED_POKEMON_ERROR -> {
				response.status = HttpStatus.BAD_REQUEST.value()
				result.name
			}
			PokemonRepositoryControllerCodes.OK -> {
				response.status = HttpStatus.OK.value()
				result.name
			}
			else -> {
				response.status = HttpStatus.BAD_REQUEST.value()
				PokemonRepositoryControllerCodes.UNKNOWN_ERROR.name
			}
		}
	}
}
