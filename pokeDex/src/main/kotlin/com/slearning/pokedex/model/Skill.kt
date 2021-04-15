package com.slearning.pokedex.model

class Skill(
    private var name: String,
    private var type: Int,
    private var description: String,
    private var damage: Int
){

    /* Getters and Setters */
    fun getName(): String = name

    fun setName(name: String): Skill {
        this.name = name
        return this
    }

    fun getDamage(): Int = damage

    fun setDamage(damage: Int): Skill {
        this.damage = damage
        return this
    }

    fun getType(): Int = type

    fun setType(type: Int): Skill {
        this.type = type
        return this
    }

    fun getDescription(): String = description

    fun setDescription(description: String): Skill {
        this.description = description
        return this
    }
}