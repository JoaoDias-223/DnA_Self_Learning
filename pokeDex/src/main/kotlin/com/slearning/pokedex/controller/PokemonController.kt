package com.slearning.pokedex.controller

import com.slearning.pokedex.model.bodyTemplates.PokemonBodyTemplate
import com.slearning.pokedex.model.bodyTemplates.SkillBodyTemplate

class PokemonController {

    companion object {
        const val OK = 0
        const val DUPLICATE_POKEMON_ERROR = 1
        const val UNREGISTERED_SKILL_ERROR = 2
        const val TYPE_AND_SKILL_MISMATCH = 3

        @JvmStatic
        fun createPokemon(newPokemonData: PokemonBodyTemplate): Int {
            println("New Pokemon Data: $newPokemonData")

            val pokemonDB = DatabaseController(url="./src/main/resources/pokemons.db", user="", pwd="", useFile=true)
            val pokemons = pokemonDB.getMutableMapOfDeserializedData<PokemonBodyTemplate>()

            val skillsDB = DatabaseController(url="./src/main/resources/skills.db", user="", pwd="", useFile=true)
            val skills = skillsDB.getMutableMapOfDeserializedData<SkillBodyTemplate>()

            return when {
                isPokemonAlreadyRegistered(newPokemonData, pokemons) -> DUPLICATE_POKEMON_ERROR
                arePokemonSkillsUnregisteredInDB(newPokemonData, skills) -> UNREGISTERED_SKILL_ERROR
                arePokemonTypeAndSkillsNotMatching(newPokemonData) -> TYPE_AND_SKILL_MISMATCH
                else -> {
                    pokemonDB.insertNewDeserializedData(newPokemonData)

                    OK
                }
            }

        }

        private fun isPokemonAlreadyRegistered(pokemon: PokemonBodyTemplate, pokemons: MutableMap<String, PokemonBodyTemplate>): Boolean {
            return pokemons.containsKey("${pokemon.hashCode()}")
        }

        private fun arePokemonSkillsUnregisteredInDB(pokemon: PokemonBodyTemplate, skills: MutableMap<String, SkillBodyTemplate>): Boolean {
            var counter = 0
            pokemon.skills.forEach { pokemonSkillID ->
                skills.forEach { (databaseSkillID, _) ->
                    counter += if("$pokemonSkillID" == databaseSkillID){ 1 } else { 0 }
                }
            }
            println("counter: $counter")
            return counter != pokemon.skills.size
        }

        private fun arePokemonTypeAndSkillsNotMatching(pokemon: PokemonBodyTemplate): Boolean {
            var counter = 0
            pokemon.type.forEach { type ->
                pokemon.skills.forEach { skill ->
                    counter += if(type == skill){ 1 } else { 0 }
                }
            }

            return counter != pokemon.type.size
        }
    }
}