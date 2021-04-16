package com.slearning.pokedex

import com.slearning.pokedex.controller.*
import com.slearning.pokedex.model.*
import com.slearning.pokedex.model.bodyTemplates.PokemonBodyTemplate
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

class PokemonControllerTests {
    @MockK
    lateinit var pokemonDBController: DatabaseController

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `When creating a Pokemon that's not registered in the DB, it should register it in the DB`() {
        //Arrange
        val newPokemon = PokemonBodyTemplate(name="Pikachu", type= arrayOf(1), description="A really bid lightning rat", skills=arrayOf(1))
        val connectionInfo = DatabaseConnectionInfo("", "", "", false)
        val emptyDatabase = Database("{}")

        every { pokemonDBController.getMutableMapOfDeserializedData<PokemonBodyTemplate>() } returns mutableMapOf()
        every { pokemonDBController.insertNewDeserializedData(newPokemon) } returns emptyDatabase

        val skillsDBController: DatabaseController = mockk()
        val skillsJsonString = """
            {"1":{"name":"Lightning Shock","type":"1","description":"A lightning bolt straight towards the enemy","damage":"200"}}
        """.trimIndent()
        val skillsDB = Database(skillsJsonString)
        every { pokemonDBController.getMutableMapOfDeserializedData<PokemonBodyTemplate>() } returns skillsDB.getMutableMapOfDeserializedData()
        every { pokemonDBController.insertNewDeserializedData(newPokemon) } returns skillsDB

        //Act
        val result = PokemonController.createPokemon(newPokemon, pokemonDBController, skillsDBController)

        //Assert
        Assertions.assertEquals(1, result)
    }

    @Test
    fun `When creating a Pokemon that's already registered in the DB, it should NOT register it again in the DB`() {
        TODO()
    }

    @Test
    fun `When creating a Pokemon with mismatching element types and skills types, it should NOT register it in the DB`(){
        TODO()
    }

    @Test
    fun `When creating a Pokemon - 1`(){
        TODO()
    }

    @Test
    fun `When creating a Pokemon - 2`(){
        TODO()
    }

    @Test
    fun `When creating a Pokemon - 3`(){
        TODO()
    }
}