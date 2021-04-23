package com.slearning.pokedex.model.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SkillDTO(
    var name: String,
    var type: Int,
    var description: String,
    var damage: Int
)
