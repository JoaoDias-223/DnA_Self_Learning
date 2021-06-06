package com.slearning.pokedex.model

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

import javax.persistence.*

@Entity(name = "skills")
@Table(name = "skills", schema = "pokedex")
class SkillEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_sequence")
    @SequenceGenerator(name="skill_sequence", sequenceName = "SKL")
    @Column(name = "id", updatable = false, nullable = false)
    var id: String? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @Column(name = "description", nullable = false)
    var description: String? = null,

    @ManyToMany
    @JoinTable(
        name = "skill_has_type",
        joinColumns = [JoinColumn(name = "skill_id")],
        inverseJoinColumns = [JoinColumn(name = "type_id")]
    )
    var types: Set<TypeEntity> = setOf(),

    @Column (name = "action", nullable = false)
    var action: String? = null,

    @Column(name = "action_points", nullable = false)
    var actionPoints: Int = 0,

    @ManyToMany(
        mappedBy = "skills",
        cascade = [CascadeType.ALL]
    )
    var pokemons: Set<PokemonEntity> = setOf()
)