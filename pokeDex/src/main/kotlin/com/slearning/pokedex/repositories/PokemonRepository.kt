package com.slearning.pokedex.repositories

import com.slearning.pokedex.model.Pokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository: JpaRepository<Pokemon, String>
{
    fun existsByIdOrNameAndDescription(id: String, name: String, description: String): Boolean //happy accident
}
