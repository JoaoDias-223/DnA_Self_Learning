package com.slearning.pokedex.model

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

import javax.persistence.*

@Entity
@Table(name = "skills", schema = "pokedex")
class SkillEntity(
    @Id
    @GeneratedValue
    @Column(name = "skill_id", updatable = false, nullable = false)
    var id: Long = 0,

    @Column(name = "skill_name", nullable = false, insertable = false, )
    var name: String? = null,

    @Column(name = "skill_description", nullable = false)
    var description: String? = null,

    @Column(name = "skill_damage", nullable = false)
    var damage: Int = 0
)