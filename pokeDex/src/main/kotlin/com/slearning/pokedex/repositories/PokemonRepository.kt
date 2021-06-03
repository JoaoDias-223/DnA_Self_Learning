package com.slearning.pokedex.repositories

import com.slearning.pokedex.model.PokemonEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository: JpaRepository<PokemonEntity, Long> {

//    @Query("select p from pokemon")
//    fun existsPokemonWithGivenNameAndTypesAndDescriptionAndSkills(name: String,
//                                                                  types: ArrayList<Int>,
//                                                                  description: String,
//                                                                  skills: ArrayList<Long>): Boolean
}
