package com.slearning.pokedex

import com.slearning.pokedex.controller.Serializer.fromJsonToMutableMapOfStringAndObject
import com.slearning.pokedex.model.dtos.PokemonDTO
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be true`
import org.amshove.kluent.`should not be null`
import org.junit.jupiter.api.Test


class SerializerTests {

    @Test
    fun `serializes JSON to a MutableMap of String and PokemonDTO`() {
        val json = """
            {"833325606":{"name":"Pikachu","type":[1],"description":"A really big lightning rat","skills":[1]}}
        """.trimIndent()

        val result = json.fromJsonToMutableMapOfStringAndObject<PokemonDTO>()

        result.size `should be equal to` 1

        result.containsKey("833325606").`should be true`()

        result["833325606"].`should not be null`()

        result["833325606"]?.apply {
            name `should be equal to` "Pikachu"
            types.contains(1).`should be true`()
            description `should be equal to` "A really big lightning rat"
            skills.contains(1).`should be true`()
        }
    }
}