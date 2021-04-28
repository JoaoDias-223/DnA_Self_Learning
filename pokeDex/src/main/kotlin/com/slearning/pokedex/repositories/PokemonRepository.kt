package com.slearning.pokedex.repositories

import com.slearning.pokedex.model.Pokemon
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository: JpaRepository<Pokemon, Long> { }
