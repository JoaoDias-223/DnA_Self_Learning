package com.slearning.pokedex.controller

import com.slearning.pokedex.databases.PokemonDatabase
import com.slearning.pokedex.databases.SkillDatabase
import com.slearning.pokedex.model.dtos.PokemonDTO
import com.slearning.pokedex.model.dtos.SkillDTO
import org.springframework.stereotype.Component

@Component
class PokemonRepositoryController(private val pokemonDatabase: PokemonDatabase,
                                  private val skillDatabase: SkillDatabase
) {

    companion object {
        const val OK = 0
        const val DUPLICATE_POKEMON_ERROR = 1
        const val UNREGISTERED_SKILL_ERROR = 2
        const val TYPE_AND_SKILL_MISMATCH = 3
        const val UNREGISTERED_POKEMON_ERROR = 5
    }

    fun createPokemon(pokemonData: PokemonDTO): Int {
        val pokemons: MutableMap<String, PokemonDTO> = pokemonDatabase.getData()
        val skills: MutableMap<String, SkillDTO> = skillDatabase.getData()

        return when {
            isPokemonAlreadyRegistered(pokemonData, pokemons) -> DUPLICATE_POKEMON_ERROR
            arePokemonSkillsUnregisteredInDB(pokemonData, skills) -> UNREGISTERED_SKILL_ERROR
            arePokemonTypeAndSkillsNotMatching(pokemonData) -> TYPE_AND_SKILL_MISMATCH
            else -> {
                pokemonDatabase.insertData(pokemonData.hashCode().toString(), pokemonData)
                OK
            }
        }
    }

    fun getPokemons(): MutableMap<String, PokemonDTO> {
        return pokemonDatabase.getData()
    }

    fun getPokemonById(id: String): PokemonDTO? {
        return pokemonDatabase.getData()[id]
    }

    fun updatePokemonById(id: String, newPokemonData: PokemonDTO): Int {
        val pokemons: MutableMap<String, PokemonDTO> = pokemonDatabase.getData()
        val skills: MutableMap<String, SkillDTO> = skillDatabase.getData()

        return when {
            !pokemonDatabase.hasId(id) -> UNREGISTERED_POKEMON_ERROR
            isPokemonAlreadyRegistered(newPokemonData, pokemons) -> DUPLICATE_POKEMON_ERROR
            arePokemonSkillsUnregisteredInDB(newPokemonData, skills) -> UNREGISTERED_SKILL_ERROR
            arePokemonTypeAndSkillsNotMatching(newPokemonData) -> TYPE_AND_SKILL_MISMATCH
            else -> {
                pokemonDatabase.insertData(id, newPokemonData)
                OK
            }
        }
    }

    fun deletePokemonById(id: String): Int {
        return when {
            !pokemonDatabase.hasId(id) -> UNREGISTERED_POKEMON_ERROR
            else -> {
                pokemonDatabase.deleteById(id)
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