package com.slearning.pokedex.model.bodyTemplates

import kotlinx.serialization.Serializable

@Serializable
data class PokemonBodyTemplate(
    val name: String,
    val type: Array<Int>,
    val description: String,
    val skills: Array<Int>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonBodyTemplate

        if (name != other.name) return false
        if (!type.contentEquals(other.type)) return false
        if (description != other.description) return false
        if (!skills.contentEquals(other.skills)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.contentHashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + skills.contentHashCode()
        return result
    }
}
