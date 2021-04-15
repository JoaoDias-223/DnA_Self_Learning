package com.slearning.pokedex.controller

import com.slearning.pokedex.model.bodyTemplates.PokemonBody
import com.slearning.pokedex.model.bodyTemplates.SkillBody

class PokemonController {

    companion object {
        @JvmStatic
        fun createPokemon(newPokemonData: PokemonBody): Boolean {
            println("New Pokemon Data: $newPokemonData")

            val pokemonDB = DatabaseController(url="./src/main/resources/pokemons.db", user="", pwd="", useFile=true)
            val pokemons = pokemonDB.getMutableMapOfDeserializedData<PokemonBody>()

            val skillsDB = DatabaseController(url="./src/main/resources/skills.db", user="", pwd="", useFile=true)
            val skills = skillsDB.getMutableMapOfDeserializedData<SkillBody>()

            pokemonDB.insertNewDeserializedData(newPokemonData)

            return true
        }
    }
}