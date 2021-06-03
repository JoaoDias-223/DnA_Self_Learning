package com.slearning.pokedex.model

import javax.persistence.*

@Entity
@Table(name = "pokemons", schema = "pokedex")
class PokemonEntity (
    @Id
    @GeneratedValue
    @Column(name = "pokemon_id", updatable = false, nullable = false)
    var id: Long = 0,

    @Column(name = "pokemon_name", nullable = false)
    var name: String? = null,

    @ManyToMany
    var types: Set<TypeEntity> = setOf(),

    @Column(name = "pokemon_description", nullable = false)
    var description: String? = null,

    @ManyToMany
    var skills: Set<SkillEntity> = setOf()

)