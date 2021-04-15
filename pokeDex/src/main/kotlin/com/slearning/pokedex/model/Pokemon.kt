package com.slearning.pokedex.model

class Pokemon(
    private var name: String,
    private var type: Array<Int>,
    private var description: String,
    private var skills: Array<Skill> = arrayOf())
{

    /* Getters and Setters */
    fun getName(): String = name

    fun setName(name: String): Pokemon {
        this.name = name
        return this
    }

    fun getType(): Array<Int> = type

    fun setType(type: Array<Int>): Pokemon {
        this.type = type
        return this
    }

    fun getDescription(): String = description

    fun setDescription(description: String): Pokemon {
        this.description = description
        return this
    }

    fun getSkills(): Array<Skill> = skills

    fun setSkills(skills: Array<Skill>): Pokemon {
        this.skills = skills
        return this
    }
}