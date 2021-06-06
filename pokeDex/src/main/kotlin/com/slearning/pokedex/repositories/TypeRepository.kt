package com.slearning.pokedex.repositories

import com.slearning.pokedex.model.Type
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository : JpaRepository<Type, String>