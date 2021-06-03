package com.slearning.pokedex.repositories

import org.springframework.data.jpa.repository.JpaRepository
import com.slearning.pokedex.model.SkillEntity

interface SkillRepository: JpaRepository<SkillEntity, Long> { }
