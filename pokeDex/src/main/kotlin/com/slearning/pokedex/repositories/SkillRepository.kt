package com.slearning.pokedex.repositories

import org.springframework.data.jpa.repository.JpaRepository
import com.slearning.pokedex.model.Skill
import org.springframework.stereotype.Repository

@Repository
interface SkillRepository: JpaRepository<Skill, String>
