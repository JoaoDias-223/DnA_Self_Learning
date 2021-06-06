package com.slearning.pokedex

import com.slearning.pokedex.controller.Serializer.fromJsonToList
import com.slearning.pokedex.model.Pokemon
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be true`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be null`
import org.junit.jupiter.api.Test


class SerializerTests {

    @Test
    fun `serializes JSON to a list of Pokemon`() {
        val json = """
            [
                {
                    "id": "POK_1",
                    "name": "Pikachu",
                    "description": "A big yellow rat capable of shooting lightning",
                    "types": [
                        {
                            "id": "TYP_1",
                            "name": "ELECTRIC"
                        }
                    ],
                    "skills": [
                        {
                            "id": "SKL_1",
                            "name": "Static",
                            "description": "Paralyzes on contact",
                            "types": [
                                {
                                    "id": "TYP_1",
                                    "name": "ELECTRIC"
                                }
                            ],
                            "action": "OFFENSE",
                            "actionPoints": 300
                        }
                    ]
                }
            ]
        """.trimIndent()

        val result = json.fromJsonToList<Pokemon>()

        result.size `should be equal to` 1

        result[0].apply {
            id!! `should be equal to` "POK_1"
            name!! `should be equal to` "Pikachu"
            description!! `should be equal to` "A big yellow rat capable of shooting lightning"
            types.size `should be equal to` 1
            types.first().apply {
                id!! `should be equal to` "TYP_1"
                name!! `should be equal to` "ELECTRIC"
            }
            skills.size `should be equal to` 1
            skills.first().apply {
                id!! `should be equal to` "SKL_1"
                name!! `should be equal to` "Static"
                description!! `should be equal to` "Paralyzes on contact"
                types.first().apply {
                    id!! `should be equal to` "TYP_1"
                    name!! `should be equal to` "ELECTRIC"
                }
                action!! `should be equal to` "OFFENSE"
                actionPoints `should be equal to` 300
            }
        }
    }
}