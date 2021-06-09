package com.slearning.pokedex

import com.slearning.pokedex.controller.Serializer.fromJsonToList
import com.slearning.pokedex.controller.Serializer.toJson
import com.slearning.pokedex.model.Pokemon
import com.slearning.pokedex.model.Skill
import com.slearning.pokedex.model.Type
import com.slearning.pokedex.resources.EntityFactory
import com.slearning.pokedex.resources.JsonFactory
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test


class SerializerTests {

    @Test
    fun `serializes JSON to a list of Pokemon`() {
        val expectedList = listOf(EntityFactory.createPokemon())

        val json = """
            [
                ${JsonFactory.createPokemon()}
            ]
        """.trimIndent()
        val result = json.fromJsonToList<Pokemon>()

        result.size `should be equal to` 1

        result[0].apply {
            id!! `should be equal to` expectedList[0].id!!
            name!! `should be equal to` expectedList[0].name!!
            description!! `should be equal to` expectedList[0].description!!
            types.size `should be equal to` expectedList[0].types.size
            types.first().apply {
                id!! `should be equal to` expectedList[0].types.first().id!!
                name!! `should be equal to` expectedList[0].types.first().name!!
            }
            skills.size `should be equal to` expectedList[0].skills.size
            skills.first().apply {
                id!! `should be equal to` expectedList[0].skills.first().id!!
                name!! `should be equal to` expectedList[0].skills.first().name!!
                description!! `should be equal to` expectedList[0].skills.first().description!!
                types.first().apply {
                    id!! `should be equal to` expectedList[0].skills.first().types.first().id!!
                    name!! `should be equal to` expectedList[0].skills.first().types.first().name!!
                }
                action!! `should be equal to` expectedList[0].skills.first().action!!
                actionPoints `should be equal to` expectedList[0].skills.first().actionPoints
            }
        }
    }

    @Test
    fun `serializes List to Json`() {
        val expectedJson = "[${JsonFactory.createPokemon()}]"

        val list = listOf(EntityFactory.createPokemon())
        val result = list.toJson()

        result `should be equal to` expectedJson
    }

    @Test
    fun `serializes Pokemon to Json`() {
        val expectedJson = JsonFactory.createPokemon()

        val pokemon = EntityFactory.createPokemon()
        val result = pokemon.toJson<Pokemon>()

        result `should be equal to` expectedJson
    }

    @Test
    fun `serializes Skill to Json`() {
        val expectedJson = JsonFactory.createSkill()

        val skill = EntityFactory.createSkill()
        val result = skill.toJson<Skill>()

        result `should be equal to` expectedJson
    }

    @Test
    fun `serializes Type to Json`() {
        val expectedJson = JsonFactory.createType()

        val skill = EntityFactory.createType()
        val result = skill.toJson<Type>()

        result `should be equal to` expectedJson
    }
}