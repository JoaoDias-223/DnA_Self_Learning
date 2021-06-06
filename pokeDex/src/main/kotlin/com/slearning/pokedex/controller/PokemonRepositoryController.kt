package com.slearning.pokedex.controller

import com.slearning.pokedex.model.Pokemon
import com.slearning.pokedex.repositories.PokemonRepository
import com.slearning.pokedex.repositories.SkillRepository
import com.slearning.pokedex.repositories.TypeRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PokemonRepositoryController(
    private val pokemonRepository: PokemonRepository,
    private val skillRepository: SkillRepository,
    private val typeRepository: TypeRepository,
) {

    companion object {
        const val OK = 0
        const val DUPLICATE_POKEMON_ERROR = 1
        const val UNREGISTERED_SKILL_ERROR = 2
        const val TYPE_AND_SKILL_MISMATCH = 3
        const val UNREGISTERED_POKEMON_ERROR = 5
    }

    fun createPokemon(pokemonData: Pokemon): Int {
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

    fun getPokemons(): List<Pokemon> {
        val pokemonEntities = pokemonRepository.findAll()

        pokemonEntities.forEach {pokemon->
            println("pokemon_id: ${pokemon.id}")
            pokemon.types.forEach { type ->
                println("\ttype_id: ${type.id}")
            }
            pokemon.skills.forEach { skill ->
                println("\tskill_id: ${skill.id}")
            }

        }

        return pokemonEntities
    }

    fun getPokemonById(id: Long): Pokemon? {
        return null
    }

    fun updatePokemonById(id: Long, newPokemonData: Pokemon): Int {
        return UNREGISTERED_POKEMON_ERROR
    }

    fun deletePokemonById(id: Long): Int {
        return UNREGISTERED_POKEMON_ERROR
    }

    fun isPokemonAlreadyRegistered(pokemon: Pokemon): Boolean {
        val pokemonMatcher = matchingAny()
            .withIgnorePaths("pokemon_id")
            .withMatcher("pokemon_name", GenericPropertyMatchers.exact())
            .withMatcher("pokemon_types", GenericPropertyMatchers.contains())
            .withMatcher("pokemon_description", GenericPropertyMatchers.exact())
            .withMatcher("pokemon_skills", GenericPropertyMatchers.contains())

        var isPokemonRegistered = true
        try {
            val example = Example.of(pokemon, pokemonMatcher)
            isPokemonRegistered = pokemonRepository.exists(example)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return isPokemonRegistered
    }

    fun arePokemonSkillsUnregisteredInDB(pokemon: Pokemon): Boolean {
        return false
    }

    fun arePokemonTypeAndSkillsNotMatching(pokemon: Pokemon): Boolean {
        return false
    }
}