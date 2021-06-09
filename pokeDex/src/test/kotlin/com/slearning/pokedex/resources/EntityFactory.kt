package com.slearning.pokedex.resources

import com.slearning.pokedex.model.Pokemon
import com.slearning.pokedex.model.Skill
import com.slearning.pokedex.model.Type

class EntityFactory {
    companion object {
        fun createPokemon(
            id: String = "POKEMON_ID",
            name: String = "POKEMON_NAME",
            description: String = "POKEMON_DESCRIPTION",
            types: MutableSet<Type> = mutableSetOf(createType(id="POKEMON_TYPE_ID", name="POKEMON_TYPE_NAME")),
            skills: MutableSet<Skill> = mutableSetOf(createSkill())
        ): Pokemon {
            return Pokemon(id, name, description, types, skills)
        }

        fun createType(
            id: String = "TYPE_ID",
            name: String = "TYPE_NAME"
        ): Type {
            return Type(id, name)
        }

        fun createSkill(
            id: String = "SKILL_ID",
            name: String = "SKILL_NAME",
            description: String = "SKILL_DESCRIPTION",
            types: MutableSet<Type> = mutableSetOf(createType(id="SKILL_TYPE_ID", name="SKILL_TYPE_NAME")),
            action: String = "SKILL_ACTION",
            actionPoints: Int = 999999
        ): Skill {
            return Skill(id, name, description, types, action, actionPoints)
        }
    }
}