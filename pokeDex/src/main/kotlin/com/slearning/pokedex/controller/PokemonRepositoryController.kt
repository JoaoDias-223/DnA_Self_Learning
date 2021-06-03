package com.slearning.pokedex.controller

import com.slearning.pokedex.model.PokemonEntity
import com.slearning.pokedex.model.dtos.PokemonDTO
import com.slearning.pokedex.repositories.PokemonRepository
import com.slearning.pokedex.repositories.SkillRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher.*
import org.springframework.stereotype.Component

@Component
class PokemonRepositoryController(private val pokemonRepository: PokemonRepository,
                                  private val skillRepository: SkillRepository
) {

    companion object {
        const val OK = 0
        const val DUPLICATE_POKEMON_ERROR = 1
        const val UNREGISTERED_SKILL_ERROR = 2
        const val TYPE_AND_SKILL_MISMATCH = 3
        const val UNREGISTERED_POKEMON_ERROR = 5
    }

    fun createPokemon(pokemonData: PokemonDTO): Int {
//        val pokemon = Pokemon()
//        pokemon.name = pokemonData.name
//        pokemon.types = ArrayList()
//        pokemon.types.addAll(pokemonData.types)
//        pokemon.description = pokemonData.description
//        pokemon.skills = ArrayList()
//        pokemon.skills.addAll(pokemonData.skills)

//        return when {
//            isPokemonAlreadyRegistered(pokemon) -> DUPLICATE_POKEMON_ERROR
//            arePokemonSkillsUnregisteredInDB(pokemon) -> UNREGISTERED_SKILL_ERROR
//            arePokemonTypeAndSkillsNotMatching(pokemon) -> TYPE_AND_SKILL_MISMATCH
//            else -> {
//                pokemonRepository.save(pokemon)
//                OK
//            }
//        }
        return 0
    }

    fun getPokemons(): MutableMap<String, PokemonDTO> {
//        val listOfPokemons: MutableList<Pokemon> = pokemonRepository.findAll()
//
//        val mapOfPokemons: MutableMap<String, PokemonDTO> = mutableMapOf()
//
//        listOfPokemons.forEach {
//            mapOfPokemons[it.id.toString()] = PokemonDTO(it.name, it.types, it.description, it.skills)
//        }
//
//        return mapOfPokemons
        return mutableMapOf<String, PokemonDTO>()
    }

    fun getPokemonById(id: Long): PokemonEntity? {
        return pokemonRepository.findById(id).orElseGet{ null }
    }

    fun updatePokemonById(id: Long, newPokemonData: PokemonDTO): Int {
//        val pokemon = Pokemon()
//        pokemon.name = newPokemonData.name
//        pokemon.types = newPokemonData.types
//        pokemon.description = newPokemonData.description
//        pokemon.skills = newPokemonData.skills
//        pokemon.id = id
//
//        return when {
//            !pokemonRepository.existsById(id) -> UNREGISTERED_POKEMON_ERROR
//            isPokemonAlreadyRegistered(pokemon) -> DUPLICATE_POKEMON_ERROR
//            arePokemonSkillsUnregisteredInDB(pokemon) -> UNREGISTERED_SKILL_ERROR
//            arePokemonTypeAndSkillsNotMatching(pokemon) -> TYPE_AND_SKILL_MISMATCH
//            else -> {
//                pokemonRepository.saveAndFlush(pokemon)
//                OK
//            }
//        }
        return 0
    }

    fun deletePokemonById(id: Long): Int {
        return when {
            !pokemonRepository.existsById(id) -> UNREGISTERED_POKEMON_ERROR
            else -> {
                pokemonRepository.deleteById(id)
                OK
            }
        }
    }

    fun isPokemonAlreadyRegistered(pokemonEntity: PokemonEntity): Boolean {
        val pokemonMatcher = matchingAny()
            .withIgnorePaths("pokemon_id")
            .withMatcher("pokemon_name", GenericPropertyMatchers.exact())
            .withMatcher("pokemon_types", GenericPropertyMatchers.contains())
            .withMatcher("pokemon_description", GenericPropertyMatchers.exact())
            .withMatcher("pokemon_skills", GenericPropertyMatchers.contains())

        var isPokemonRegistered = true
        try {
            val example = Example.of(pokemonEntity, pokemonMatcher)
            isPokemonRegistered = pokemonRepository.exists(example)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return isPokemonRegistered
    }

    fun arePokemonSkillsUnregisteredInDB(pokemonEntity: PokemonEntity): Boolean {
//        val counter = 0
//        pokemon.skills.forEach { pokemonSkillID ->
//            var isSkillRegistered = false;
//
//            try {
//                isSkillRegistered = skillRepository.existsById(pokemonSkillID)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//            if (isSkillRegistered) {
//                return false
//            }
//        }
        return false
    }

    fun arePokemonTypeAndSkillsNotMatching(pokemonEntity: PokemonEntity): Boolean {
//        println("${this.javaClass.name}::arePokemonTypeAndSkillsNotMatching() called")
//
//        var counter = 0
//        pokemon.types.forEach { type ->
//            println("\tpokemon type: $type")
//            pokemon.skills.forEach { skillID ->
//                println("\tpokemon skill ID: $skillID")
//                val optional = skillRepository.findById(skillID)
//                if (optional.isPresent) {
//                    counter += if(type == optional.get().type) { 1 } else { 0 }
//                }
//            }
//        }

        return false
    }
}