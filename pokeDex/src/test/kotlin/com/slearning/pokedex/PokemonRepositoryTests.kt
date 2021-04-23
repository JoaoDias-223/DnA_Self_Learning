package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonRepository
import com.slearning.pokedex.databases.PokemonDatabase
import com.slearning.pokedex.databases.SkillDatabase
import com.slearning.pokedex.model.dtos.PokemonDTO
import io.mockk.*
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PokemonControllerTests {

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `When creating a Pokemon that's not registered in the DB, it should be registered in the DB`(){
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))

        // Act
        val pokemonRepository = PokemonRepository(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepository.OK
    }

    @Test
    fun `When creating a Pokemon that's already registered in the DB, it should NOT be registered in the DB`() {
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))

        pokemonDB.insertData(pokemonData)

        // Act
        val pokemonRepository = PokemonRepository(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepository.DUPLICATE_POKEMON_ERROR
    }

    @Test
    fun `When creating a Pokemon with mismatching element types and skills types, it should NOT be registered in the DB`() {
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(3), description="A lightning rat", skills=listOf(1))

        // Act
        val pokemonRepository = PokemonRepository(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepository.TYPE_AND_SKILL_MISMATCH
    }

    @Test
    fun `When creating a Pokemon with an unregistered skill, it should NOT be registered in the DB`() {
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(2))

        // Act
        val pokemonRepository = PokemonRepository(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepository.UNREGISTERED_SKILL_ERROR
    }
}