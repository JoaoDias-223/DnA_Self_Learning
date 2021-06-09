package com.slearning.pokedex.model

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity(name = "types")
@Table(name = "types", schema = "pokedex")
class Type(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    var id: String? = UUID.randomUUID().toString(),

    @Column(name = "name", nullable = false)
    var name: String? = null,

    ): Serializable
{
    override fun toString(): String {
        return """
            {
                "id":"$id",
                "name":"$name"
            }
        """.trimIndent()
    }
}
