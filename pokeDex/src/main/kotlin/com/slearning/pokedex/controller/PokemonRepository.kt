package com.slearning.pokedex.controller

import com.slearning.pokedex.model.bodyTemplates.PokemonDTO
import com.slearning.pokedex.model.bodyTemplates.SkillDTO
import org.springframework.stereotype.Component

@Component
class PokemonController(private val pokemonDatabaseController: DatabaseController,
                        private val skillDatabaseController: DatabaseController
) {

    companion object {
        const val OK = 0
        const val DUPLICATE_POKEMON_ERROR = 1
        const val UNREGISTERED_SKILL_ERROR = 2
        const val TYPE_AND_SKILL_MISMATCH = 3
    }

    fun createPokemon(newPokemonData: PokemonDTO): Int {
        val pokemons: MutableMap<String, PokemonDTO> = pokemonDatabaseController.getMutableMapOfDeserializedData()
        val skills: MutableMap<String, SkillDTO> = skillDatabaseController.getMutableMapOfDeserializedData()

        return when {
            isPokemonAlreadyRegistered(newPokemonData, pokemons) -> DUPLICATE_POKEMON_ERROR
            arePokemonSkillsUnregisteredInDB(newPokemonData, skills) -> UNREGISTERED_SKILL_ERROR
            arePokemonTypeAndSkillsNotMatching(newPokemonData) -> TYPE_AND_SKILL_MISMATCH
            else -> {
                pokemonDatabaseController.insertNewDeserializedData(newPokemonData)
                OK
            }
        }
    }

    fun isPokemonAlreadyRegistered(pokemon: PokemonDTO, pokemons: MutableMap<String, PokemonDTO>): Boolean {
        return pokemons.containsKey("${pokemon.hashCode()}")
    }

    fun arePokemonSkillsUnregisteredInDB(pokemon: PokemonDTO, skills: MutableMap<String, SkillDTO>): Boolean {
        var counter = 0
        pokemon.skills.forEach { pokemonSkillID ->
            skills.forEach { (databaseSkillID, _) ->
                counter += if("$pokemonSkillID" == databaseSkillID){ 1 } else { 0 }
            }
        }
        return counter != pokemon.skills.size
    }

    fun arePokemonTypeAndSkillsNotMatching(pokemon: PokemonDTO): Boolean {
        var counter = 0
        pokemon.type.forEach { type ->
            pokemon.skills.forEach { skill ->
                counter += if(type == skill){ 1 } else { 0 }
            }
        }

        return counter != pokemon.type.size
    }
}