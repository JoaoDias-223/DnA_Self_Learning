package com.slearning.pokedex.resources

import com.slearning.pokedex.controller.Serializer.toJson
import com.slearning.pokedex.model.Skill

class JsonFactory {
    companion object{
        fun createPokemon(
            id: String = "POKEMON_ID",
            name: String = "POKEMON_NAME",
            description: String = "POKEMON_DESCRIPTION",
            types: Set<String> = setOf(createType(id="POKEMON_TYPE_ID", name="POKEMON_TYPE_NAME")),
            skills: Set<String> = setOf(createSkill())
        ): String {
            val typesStr = getFormattedStringFromSet(types)
            val skillsStr = getFormattedStringFromSet(skills)

            return "{\"id\":\"$id\",\"name\":\"$name\",\"description\":\"$description\",\"types\":[$typesStr]," +
                    "\"skills\":[$skillsStr]}"
        }

        fun createType(
            id: String = "TYPE_ID",
            name: String = "TYPE_NAME"
        ): String {
            return "{\"id\":\"$id\",\"name\":\"$name\"}"
        }

        fun createSkill(
            id: String = "SKILL_ID",
            name: String = "SKILL_NAME",
            description: String = "SKILL_DESCRIPTION",
            types: Set<String> = setOf(createType(id = "SKILL_TYPE_ID", name = "SKILL_TYPE_NAME")),
            action: String = "SKILL_ACTION",
            actionPoints: Int = 999999
        ): String {
            val typesStr = getFormattedStringFromSet(types)

            return "{\"id\":\"$id\",\"name\":\"$name\",\"description\":\"$description\",\"types\":[$typesStr]," +
                    "\"action\":\"$action\",\"actionPoints\":$actionPoints}"
        }

        private fun getFormattedStringFromSet(set: Set<String>): String {
            var string = ""
            var setSize = set.size

            set.forEach { element ->
                string += element

                if (setSize - 1 != 0) {
                    string += ","
                }
                setSize -= 1

            }

            return string
        }
    }
}