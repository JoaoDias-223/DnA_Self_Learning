package com.slearning.pokedex.repositories

import com.slearning.pokedex.model.Pokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository: JpaRepository<Pokemon, String>
{
//    @Query(
//        value = "" +
//            "SELECT p, t, s\n" +
//            "FROM pokemons as p\n" +
//            "JOIN p.types as t\n" +
//            "JOIN p.skills as s\n" +
//            ""
//    )
//    override fun findAll(): List<Pokemon>
}
