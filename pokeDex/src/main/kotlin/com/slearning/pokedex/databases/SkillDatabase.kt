package com.slearning.pokedex.databases

import com.slearning.pokedex.model.dtos.SkillDTO
import org.springframework.stereotype.Component

@Component
class SkillDatabase {
    private val data: MutableMap<String, SkillDTO> = mutableMapOf()

    init {
        data["1"] = SkillDTO(
            name="Lightning Shock",
            type=1,
            description="A bolt of lightning straight towards the enemy",
            damage=200
        )
    }

    fun getData() = this.data

    fun insertData(skill: SkillDTO) {
        data["${skill.hashCode()}"] = skill
    }
}