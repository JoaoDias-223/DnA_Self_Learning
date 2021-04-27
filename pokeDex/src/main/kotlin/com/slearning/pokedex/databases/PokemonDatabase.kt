package com.slearning.pokedex.databases

import com.slearning.pokedex.model.dtos.PokemonDTO
import org.springframework.stereotype.Component

@Component
class PokemonDatabase {
    private val data: MutableMap<String, PokemonDTO> = mutableMapOf()

    init {
        data["-283589297"] = PokemonDTO("Pikachu", listOf(1), "A lightning rat", listOf(1))
        data["888455034"] = PokemonDTO("Raichu", listOf(1), "A big lightning rat", listOf(1))
    }

    fun getData() = this.data

    fun insertData(id: String, pokemon: PokemonDTO) {
        data[id] = pokemon
    }

    fun hasId(id: String): Boolean {
        return data.containsKey(id)
    }

    fun deleteById(id: String) {
        data.remove(id)
    }
}