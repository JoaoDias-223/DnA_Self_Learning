package com.slearning.pokedex

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
class PokemonTests {

    @Test
    fun Contructor_nameAlreadyInDatabase_nameAlreadyInDatabaseException() {
        val exception: Exception = assertThrows<NameAlreadyInDatabaseException> { Pokemon("Pikachu", Pokemon.types.LIGHTNING, "A rat that shoots lighting ") }

        val expectedMessage: String = "Name given is already in the database"
        val actualMessage: String? = exception.message

        assert(actualMessage.equals(expectedMessage))
    }
}