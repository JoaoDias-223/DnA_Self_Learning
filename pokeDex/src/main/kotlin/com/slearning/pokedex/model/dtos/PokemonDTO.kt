package com.slearning.pokedex.model.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDTO(
    val name: String,
    val types: ArrayList<Int>,
    val description: String,
    val skills: ArrayList<Long>
) {

    override fun toString(): String {

        return """
            {"name":"$name","type":${types.map { "$it" }},"description":"$description","skills":${skills.map { "$it" }}}
        """.trimIndent()
    }
}
