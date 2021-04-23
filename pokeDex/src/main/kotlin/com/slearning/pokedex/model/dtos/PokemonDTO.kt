package com.slearning.pokedex.model.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDTO(
    val name: String,
    val type: List<Int>,
    val description: String,
    val skills: List<Int>
) {

    override fun toString(): String {

        return """
            {"name":"$name","type":${type.map { "$it" }},"description":"$description","skills":${skills.map { "$it" }}}
        """.trimIndent()
    }
}
