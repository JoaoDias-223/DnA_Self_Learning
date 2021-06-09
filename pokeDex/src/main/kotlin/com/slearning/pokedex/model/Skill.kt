package com.slearning.pokedex.model

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

import javax.persistence.*

@Entity(name = "skills")
@Table(name = "skills", schema = "pokedex")
class Skill(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    var id: String? = UUID.randomUUID().toString(),

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @Column(name = "description", nullable = false)
    var description: String? = null,

    @ManyToMany
    @JoinTable(
        name = "skill_has_type",
        joinColumns = [JoinColumn(name = "skill_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "type_id", referencedColumnName = "id")]
    )
    var types: MutableSet<Type> = mutableSetOf(),

    @Column (name = "action", nullable = false)
    var action: String? = null,

    @Column(name = "action_points", nullable = false)
    var actionPoints: Int = 0,

    ): Serializable