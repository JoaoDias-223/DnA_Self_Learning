package com.slearning.pokedex

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
class PokemonTests {

    @Test
    fun `When given a Pokemon it should persists in the DB`() {
        TODO()
    }

    @Test
    fun `When given a Pokemon already in the DB it should NOT persists in the DB`() {
//        val exception: Exception = assertThrows<NameAlreadyInDatabaseException> { Pokemon("Pikachu", Pokemon.types.LIGHTNING, "A rat that shoots lighting ") }
//
//        val expectedMessage: String = "Name given is already in the database"
//        val actualMessage: String? = exception.message
//
//        assert(actualMessage.equals(expectedMessage))

        TODO()
    }
}