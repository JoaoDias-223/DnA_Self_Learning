package com.slearning.pokedex.model

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity(name = "pokemons")
@Table(name = "pokemons", schema = "pokedex")
class Pokemon (
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    var id: String? = UUID.randomUUID().toString(),

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @Column(name = "description", nullable = false)
    var description: String? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "pokemon_has_type",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "type_id", referencedColumnName = "id")]
    )
    var types: MutableSet<Type> = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "pokemon_has_skill",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "skill_id", referencedColumnName = "id")]
    )
    var skills: MutableSet<Skill> = mutableSetOf()

): Serializable