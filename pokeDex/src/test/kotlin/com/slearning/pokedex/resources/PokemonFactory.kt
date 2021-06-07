package com.slearning.pokedex.resources

import com.slearning.pokedex.model.Pokemon
import com.slearning.pokedex.model.Skill
import com.slearning.pokedex.model.Type

class PokemonFactory {
    companion object {
        fun create(
            id: String,
            name: String = "POKEMON_NAME",
            description: String = "POKEMON_DESCRIPTION",
            types: Set<Type> = setOf(),
            skills: Set<Skill> = setOf()
        ): Pokemon
        {
            return Pokemon(id, name, description, types, skills)
        }
    }
}