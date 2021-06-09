package com.slearning.pokedex.controller

import com.slearning.pokedex.controller.Serializer.toJson
import com.slearning.pokedex.controller.resources.PokemonRepositoryControllerCodes
import com.slearning.pokedex.model.Pokemon
import com.slearning.pokedex.repositories.PokemonRepository
import com.slearning.pokedex.repositories.SkillRepository
import com.slearning.pokedex.repositories.TypeRepository
import org.springframework.stereotype.Component

@Component
class PokemonRepositoryController(
    private val pokemonRepository: PokemonRepository,
    private val skillRepository: SkillRepository,
    private val typeRepository: TypeRepository,
) {

    fun getPokemons(): List<Pokemon> {
        return pokemonRepository.findAll()
    }

    fun getPokemonById(id: String): Pokemon? {
        return pokemonRepository.findById(id).orElse(null)
    }

    fun createPokemon(pokemon: Pokemon): PokemonRepositoryControllerCodes {
        return when (val result = validateIncomingPokemonCreation(pokemon)){
            PokemonRepositoryControllerCodes.OK -> {
                println(pokemon.toJson<Pokemon>())
                pokemonRepository.saveAndFlush(pokemon)
                PokemonRepositoryControllerCodes.OK
            }
            else -> result
        }
    }

    fun updatePokemonById(id: String, newPokemonData: Pokemon): PokemonRepositoryControllerCodes {
        if (!pokemonRepository.existsById(id)) {
            return PokemonRepositoryControllerCodes.UNREGISTERED_POKEMON_ERROR
        }

        val result = validatePokemonUpdate(newPokemonData)

        if (result != PokemonRepositoryControllerCodes.OK){
            return result
        }

        val pokemon: Pokemon = pokemonRepository.findById(id).orElseThrow()
        pokemon.name = newPokemonData.name
        pokemon.description = newPokemonData.description
        pokemon.types = newPokemonData.types
        pokemon.skills = newPokemonData.skills

        return PokemonRepositoryControllerCodes.OK
    }

    fun deletePokemonById(id: String): PokemonRepositoryControllerCodes {
        if (!pokemonRepository.existsById(id)) {
            return PokemonRepositoryControllerCodes.UNREGISTERED_POKEMON_ERROR
        }

        pokemonRepository.deleteById(id)

        return PokemonRepositoryControllerCodes.OK
    }

    fun validateIncomingPokemonCreation(pokemon: Pokemon): PokemonRepositoryControllerCodes {
        return when {
            isPokemonAlreadyRegistered(pokemon) -> PokemonRepositoryControllerCodes.DUPLICATE_POKEMON_ERROR
            arePokemonSkillsUnregisteredInDB(pokemon) -> PokemonRepositoryControllerCodes.UNREGISTERED_SKILL_ERROR
            arePokemonTypeAndSkillsNotMatching(pokemon) -> PokemonRepositoryControllerCodes.TYPE_AND_SKILL_MISMATCH
            else -> PokemonRepositoryControllerCodes.OK
        }
    }

    fun validatePokemonUpdate(pokemon: Pokemon): PokemonRepositoryControllerCodes {
        return when {
            arePokemonSkillsUnregisteredInDB(pokemon) -> PokemonRepositoryControllerCodes.UNREGISTERED_SKILL_ERROR
            arePokemonTypeAndSkillsNotMatching(pokemon) -> PokemonRepositoryControllerCodes.TYPE_AND_SKILL_MISMATCH
            else -> PokemonRepositoryControllerCodes.OK
        }
    }

    private fun isPokemonAlreadyRegistered(pokemon: Pokemon): Boolean {
        return pokemonRepository.existsByIdOrNameAndDescription(pokemon.id!!, pokemon.name!!, pokemon.description!!)
    }

    private fun arePokemonSkillsUnregisteredInDB(pokemon: Pokemon): Boolean {
        pokemon.skills.forEach { skill->
            if (!skillRepository.existsById(skill.id!!)) {
                return true
            }
        }
        return false
    }

    private fun arePokemonTypeAndSkillsNotMatching(pokemon: Pokemon): Boolean {

        pokemon.skills.forEach skillLoop@{ skill ->
            skill.types.forEach skillTypeLoop@{ skillType ->
                var match = false

                pokemon.types.forEach pokemonTypeLoop@{ pokemonType ->
                    if (pokemonType.id!! == skillType.id!!) {
                        match = true
                    }
                }

                if (!match) {
                    return true
                }
            }
        }

        return false
    }
}