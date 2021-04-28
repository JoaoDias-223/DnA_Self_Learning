package com.slearning.pokedex.model

import javax.persistence.*

@Entity
@Table(name = "pokemon")
class Pokemon {

    @Id
    @GeneratedValue
    var id: Int = 0;

    @Column(name = "name", nullable = false)
    lateinit var name: String;

    @Column(name = "types", nullable = false)
    lateinit var types: List<Int>;

    @Column(name = "description", nullable = false)
    lateinit var description: String;

    @Column(name = "name", nullable = false)
    lateinit var skills: List<Int>;

}