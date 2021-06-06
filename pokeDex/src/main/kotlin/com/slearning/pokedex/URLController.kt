package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonRepositoryController
import com.slearning.pokedex.controller.Serializer.toJson
import com.slearning.pokedex.model.Pokemon
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus.OK
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
@RestController
@RequestMapping("/pokemon")
class URLController (private val pokemonRepositoryController: PokemonRepositoryController){

	@GetMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
	fun getAllPokemons(response: HttpServletResponse) : String {
		response.status = OK.value()

		return pokemonRepositoryController.getPokemons().toJson()
	}

	@PostMapping("/")
	fun createPokemon(@RequestBody body: Pokemon, response: HttpServletResponse) : String {
		response.status = OK.value()
		return "Ok"
	}

	@GetMapping("/{pokemonID}")
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

	@DeleteMapping("/{pokemonID}")
	fun deletePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}

	@PutMapping("/{pokemonID}")
	fun updatePokemon(id: Int) : String {
		TODO("Not yet implemented")
	}
}
