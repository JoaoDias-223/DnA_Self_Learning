package com.slearning.pokedex

import org.springframework.web.bind.annotation.*

interface Routes {

    @GetMapping(listOfPokemon)
    fun getAllPokemons(): String

    @PostMapping(createPokemon)
    fun createPokemon(name: String, type: Array<String>): String

    @GetMapping(readPokemon)
    fun getPokemon(id: Int): String

    @DeleteMapping(deletePokemon)
    fun deletePokemon(id: Int): String

    @PutMapping(updatePokemon)
    fun updatePokemon(id: Int): String

    /*Anonymous object useful for defining urls*/
    companion object {
        private const val listOfPokemon: String = "/list_of_pokemon"
        private const val createPokemon: String = "$listOfPokemon/create"
        private const val readPokemon: String = "$listOfPokemon/{pokemonID}"
        private const val updatePokemon: String = "$listOfPokemon/update"
        private const val deletePokemon: String = "$listOfPokemon/delete"
    }
}