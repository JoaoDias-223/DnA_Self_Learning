package com.slearning.pokedex.model

import java.io.Serializable
import javax.persistence.*

@Entity(name = "pokemons")
@Table(name = "pokemons", schema = "pokedex")
class Pokemon (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_sequence")
    @SequenceGenerator(name="pokemon_sequence", sequenceName = "POK")
    @Column(name = "id", updatable = false, nullable = false)
    var id: String? = null,

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
    var types: Set<Type> = setOf(),

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "pokemon_has_skill",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "skill_id", referencedColumnName = "id")]
    )
    var skills: Set<Skill> = setOf()

): Serializable