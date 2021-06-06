package com.slearning.pokedex.model

import java.io.Serializable
import javax.persistence.*

@Entity(name = "types")
@Table(name = "types", schema = "pokedex")
class Type(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_sequence")
    @SequenceGenerator(name="type_sequence", sequenceName = "TYP")
    @Column(name = "id", updatable = false, nullable = false)
    var id: String? = null,

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
