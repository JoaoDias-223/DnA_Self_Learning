package com.slearning.pokedex

import com.slearning.pokedex.controller.PokemonRepositoryController
import com.slearning.pokedex.databases.PokemonDatabase
import com.slearning.pokedex.databases.SkillDatabase
import com.slearning.pokedex.model.dtos.PokemonDTO
import io.mockk.*
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be null`
import org.amshove.kluent.`should contain same`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PokemonRepositoryControllerTests {

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `When creating a Pokemon that's not registered in the DB, it should be registered in the DB and return OK`(){
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))

        // Act
        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepositoryController.OK
        pokemonDB.getData()[pokemonData.hashCode().toString()]?.apply {
            name `should be equal to` pokemonData.name
            type `should contain same` pokemonData.type
            description `should be equal to` pokemonData.description
            skills `should contain same` pokemonData.skills
        }
    }

    @Test
    fun `When creating a Pokemon that's already registered in the DB, it should NOT be registered in the DB and return DUPLICATE_POKEMON_ERROR`() {
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))

        pokemonDB.insertData(pokemonData.hashCode().toString(), pokemonData)

        // Act
        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepositoryController.DUPLICATE_POKEMON_ERROR
    }

    @Test
    fun `When creating a Pokemon with mismatching element types and skills types, it should NOT be registered in the DB and return TYPE_AND_SKILL_MISMATCH`() {
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(3), description="A lightning rat", skills=listOf(1))

        // Act
        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepositoryController.TYPE_AND_SKILL_MISMATCH
    }

    @Test
    fun `When creating a Pokemon with an unregistered skill, it should NOT be registered in the DB and return UNREGISTERED_SKILL_ERROR`() {
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(2))

        // Act
        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result = pokemonRepository.createPokemon(pokemonData)

        // Assert
        result `should be equal to` PokemonRepositoryController.UNREGISTERED_SKILL_ERROR
    }

    @Test
    fun `When requesting all Pokemons, it should return them`() {
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = listOf(
            PokemonDTO(name = "Pikachu", type = listOf(1), description = "A lightning rat", skills = listOf(1)),
            PokemonDTO(name = "Raichu", type = listOf(1), description = "A big lightning rat", skills = listOf(1))
        )

        pokemonData.forEach {
            println("${it.name} - ${it.hashCode()}")
            pokemonDB.insertData(it.hashCode().toString(), it)
        }

        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result: MutableMap<String, PokemonDTO> = pokemonRepository.getPokemons()

        var index = 0
        result.forEach {
            it.value.apply {
                name `should be equal to` pokemonData[index].name
                type `should contain same` pokemonData[index].type
                description `should be equal to` pokemonData[index].description
                skills `should contain same` pokemonData[index].skills
            }
            index+=1
        }
    }

    @Test
    fun `When requesting a registered Pokemon, it should return it`() {
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))
        val id = pokemonData.hashCode().toString()

        pokemonDB.insertData(id, pokemonData)

        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result: PokemonDTO? = pokemonRepository.getPokemonById(id)

        result?.apply {
            name `should be equal to` pokemonData.name
            type `should contain same` pokemonData.type
            description `should be equal to` description
            skills `should contain same` pokemonData.skills
        }
    }

    @Test
    fun `When requesting an unregistered Pokemon, it should return null`() {
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Squirtle", type= listOf(1), description="A lightning rat", skills=listOf(1))
        val id = pokemonData.hashCode().toString()

        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result: PokemonDTO? = pokemonRepository.getPokemonById(id)

        result.`should be null`()
    }

    @Test
    fun `When updating a registered Pokemon, it should update the DB and return the OK`(){
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val oldPokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))
        val newPokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lighting weird rat", skills=listOf(1))
        val id = oldPokemonData.hashCode().toString()

        pokemonDB.insertData(id, oldPokemonData)

        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result: Int = pokemonRepository.updatePokemonById(id, newPokemonData)

        result `should be equal to` PokemonRepositoryController.OK
        pokemonDB.getData()[id]?.apply {
            name `should be equal to` newPokemonData.name
            type `should contain same` newPokemonData.type
            description `should be equal to` newPokemonData.description
            skills `should contain same` newPokemonData.skills
        }
    }

    @Test
    fun `When updating a registered Pokemon with mismatching element type and skill type, it should NOT update the DB and return TYPE_AND_SKILL_MISMATCH`() {
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val oldPokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))
        val newPokemonData = PokemonDTO(name="Pikachu", type= listOf(2), description="A lighting weird rat", skills=listOf(1))
        val id = oldPokemonData.hashCode().toString()

        pokemonDB.insertData(id, oldPokemonData)

        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result: Int = pokemonRepository.updatePokemonById(id, newPokemonData)

        result `should be equal to` PokemonRepositoryController.TYPE_AND_SKILL_MISMATCH
        pokemonDB.getData()[id]?.apply {
            name `should be equal to` oldPokemonData.name
            type `should contain same` oldPokemonData.type
            description `should be equal to` oldPokemonData.description
            skills `should contain same` oldPokemonData.skills
        }
    }

    @Test
    fun `When updating a registered Pokemon with an unregistered skill, it should NOT update the DB and return UNREGISTERED_SKILL_ERROR`() {
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val oldPokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))
        val newPokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lighting weird rat", skills=listOf(2))
        val id = oldPokemonData.hashCode().toString()

        pokemonDB.insertData(id, oldPokemonData)

        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result: Int = pokemonRepository.updatePokemonById(id, newPokemonData)

        result `should be equal to` PokemonRepositoryController.UNREGISTERED_SKILL_ERROR
        pokemonDB.getData()[id]?.apply {
            name `should be equal to` oldPokemonData.name
            type `should contain same` oldPokemonData.type
            description `should be equal to` oldPokemonData.description
            skills `should contain same` oldPokemonData.skills
        }
    }

    @Test
    fun `When updating a registered Pokemon by passing an unknown ID, it should NOT update the DB and return UNREGISTERED_POKEMON_ERROR`() {
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val oldPokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))
        val newPokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lighting weird rat", skills=listOf(1))
        val id = oldPokemonData.hashCode().toString()
        val unknownId = newPokemonData.hashCode().toString()

        pokemonDB.insertData(id, oldPokemonData)

        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result: Int = pokemonRepository.updatePokemonById(unknownId, newPokemonData)

        result `should be equal to` PokemonRepositoryController.UNREGISTERED_POKEMON_ERROR
        pokemonDB.getData()[id]?.apply {
            name `should be equal to` oldPokemonData.name
            type `should contain same` oldPokemonData.type
            description `should be equal to` oldPokemonData.description
            skills `should contain same` oldPokemonData.skills
        }
    }

    @Test
    fun `When deleting a registered Pokemon, it should be deleted from the DB and return OK`() {
        // Arrange
        val pokemonDB = PokemonDatabase()
        val skillDB = SkillDatabase()
        val pokemonData = PokemonDTO(name="Pikachu", type= listOf(1), description="A lightning rat", skills=listOf(1))
        val id = pokemonData.hashCode().toString()

        pokemonDB.insertData(id, pokemonData)

        // Act
        val pokemonRepository = PokemonRepositoryController(pokemonDB, skillDB)
        val result = pokemonRepository.deletePokemonById(id)

        // Assert
        result `should be equal to` PokemonRepositoryController.OK
        pokemonDB.getData().size `should be equal to` 0
    }
}