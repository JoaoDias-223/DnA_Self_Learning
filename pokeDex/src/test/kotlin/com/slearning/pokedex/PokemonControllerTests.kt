package com.slearning.pokedex

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
class PokemonControllerTests {

    @Test
    fun `When given a Pokemon that's not in the DB it should persist in the DB`() {
        TODO()
    }

    @Test
    fun `When given a Pokemon already in the DB it should NOT persist in the DB`() {
//        val exception: Exception = assertThrows<NameAlreadyInDatabaseException> { Pokemon("Pikachu", Pokemon.types.LIGHTNING, "A rat that shoots lighting ") }
//
//        val expectedMessage: String = "Name given is already in the database"
//        val actualMessage: String? = exception.message
//
//        assert(actualMessage.equals(expectedMessage))

        TODO()
    }
}