package com.slearning.pokedex

import com.slearning.pokedex.model.dtos.PokemonDTO
import org.amshove.kluent.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*


@SpringBootTest
@AutoConfigureMockMvc
class URLControllerTests {

//    @Autowired
//    lateinit var mockMVC: MockMvc
//
//    @Test
//    fun `When receiving a POST request of a proper Pokemon, the status 200 and a description with value 'Everything's fine' are returned`() {
//        val result = mockMVC.perform(
//            post("/pokemon")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""{"name": "Pikachu", "type": ["1"], "description": "A lightning rat", "skills": ["1"]}""")
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn()
//
//        result.response.contentType?.`should be equal to`(MediaType.APPLICATION_JSON.toString())
//        result.response.contentAsString `should be equal to` "Everything's fine"
//        result.response.status `should be equal to` 200
//        // If I had an external database, I should check if the expected pokemon was registered
//    }
//
//    @Test
//    fun `When receiving a POST request of an already registered Pokemon, the status 400 and a description with value 'Pokemon's already registered' are returned`() {
//        val result = mockMVC.perform(
//            post("/pokemon")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""{"name": "Pikachu", "type": ["1"], "description": "A lightning rat", "skills": ["1"]}""")
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn()
//
//        result.response.contentType?.`should be equal to`(MediaType.APPLICATION_JSON.toString())
//        result.response.contentAsString `should be equal to` "Pokemon's already registered"
//        result.response.status `should be equal to` 400
//        // If I had an external database, I should check if the given pokemon was NOT registered
//    }
//
//    @Test
//    fun `When receiving a POST request of a Pokemon with an unregistered skill, the status 400 and a description with value 'Pokemon's skill not registered' are returned`() {
//        val result = mockMVC.perform(
//            post("/pokemon")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""{"name": "Pikachu", "type": ["1"], "description": "A lightning rat", "skills": ["2"]}""")
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn()
//
//        result.response.contentType?.`should be equal to`(MediaType.APPLICATION_JSON.toString())
//        result.response.contentAsString `should be equal to` "Pokemon's skill not registered"
//        result.response.status `should be equal to` 400
//        // If I had an external database, I should check if the given pokemon was NOT registered
//    }
//
//    @Test
//    fun `When receiving a POST request of a Pokemon with mismatching element and skill types, the status 400 and a description with value 'Pokemon's types and skills mismatching' are returned`() {
//        val result = mockMVC.perform(
//            post("/pokemon")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""{"name": "Pikachu", "type": ["2"], "description": "A lightning rat", "skills": ["1"]}""")
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn()
//
//        result.response.contentType?.`should be equal to`(MediaType.APPLICATION_JSON.toString())
//        result.response.contentAsString `should be equal to` "Pokemon's types and skills mismatching"
//        result.response.status `should be equal to` 400
//        // If I had an external database, I should check if the given pokemon was NOT registered
//    }
//
//    @Test
//    fun `When receiving GET request to list all pokemon, the status 200 and a body containing the requested Pokemon data are returned`() {
//        val pokemonData = listOf(
//            PokemonDTO(name = "Pikachu", type = listOf(1), description = "A lightning rat", skills = listOf(1)),
//            PokemonDTO(name = "Raichu", type = listOf(1), description = "A big lightning rat", skills = listOf(1))
//        )
//
//        val expectedResponse = """
//{"${pokemonData[0].hashCode()}":${pokemonData[0]},"${pokemonData[1].hashCode()}":${pokemonData[1]}}
//        """.trimIndent()
//
//        println("expectedResponse: $expectedResponse")
//
//        val result = mockMVC.perform(
//            get("/list_of_pokemon")
//        ).andReturn()
//
//        println("actualResponse: ${result.response.contentAsString}")
//
//        result.response.contentType?.`should be equal to`(MediaType.APPLICATION_JSON.toString())
//        result.response.status `should be equal to` 200
//        result.response.contentAsString `should be equal to` expectedResponse
//    }
//
//    @Test
//    fun `When receiving a registered id through a GET on list_of_pokemon, the status 200 and the pokemon data are returned`() {
//        val pokemonData = PokemonDTO(name = "Pikachu", type = listOf(1), description = "A lightning rat", skills = listOf(1))
//        val id = pokemonData.hashCode()
//        val expectedResponse = "$pokemonData"
//
//        val result = mockMVC.perform(
//            get("/list_of_pokemon/$id")
//        ).andReturn()
//
//        println("actualResponse: ${result.response.contentAsString}")
//
//        result.response.contentType?.`should be equal to`(MediaType.APPLICATION_JSON.toString())
//        result.response.status `should be equal to` 200
//        result.response.contentAsString `should be equal to` expectedResponse
//    }
//
//    @Test
//    fun `When receiving an unregistered id through a GET on list_of_pokemon, the status 404 and an empty json are returned`() {
//        val pokemonData = PokemonDTO(name = "Garry", type = listOf(1), description = "A lightning rat", skills = listOf(1))
//        val id = pokemonData.hashCode()
//        val expectedResponse = "{}"
//
//        val result = mockMVC.perform(
//            get("/list_of_pokemon/$id")
//        ).andReturn()
//
//        println("actualResponse: ${result.response.contentAsString}")
//
//        result.response.contentType?.`should be equal to`(MediaType.APPLICATION_JSON.toString())
//        result.response.status `should be equal to` 404
//        result.response.contentAsString `should be equal to` expectedResponse
//    }

}