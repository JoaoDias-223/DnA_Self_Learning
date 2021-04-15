package com.slearning.pokedex.model.bodyTemplates

import kotlinx.serialization.Serializable

@Serializable
data class SkillBodyTemplate(
    var name: String,
    var type: Int,
    var description: String,
    var damage: Int
)
