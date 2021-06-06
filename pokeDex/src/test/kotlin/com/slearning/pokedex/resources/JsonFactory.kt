package com.slearning.pokedex.resources

import com.slearning.pokedex.controller.Serializer.toJson
import com.slearning.pokedex.model.Skill
import com.slearning.pokedex.model.Type

class JsonFactory {
    companion object{
        fun createPokemon(
            id: String,
            name: String = "POKEMON_NAME",
            description: String = "POKEMON_DESCRIPTION",
            types: Set<Type> = setOf(),
            skills: Set<Skill> = setOf()
        ): String
        {
            return """
                {
                    "id": "$id",
                    "name": "$name",
                    "description": "$description",
                    "types":${types.toJson()},
                    "skills":${skills.toJson()}
                }
            """.trimIndent()
        }
    }
}