package com.slearning.pokedex.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

object Serializer{
    inline fun <reified T> String.fromJsonToList(): List<T> {
        val mapper = jacksonObjectMapper()

        return try {
            mapper.readValue(this)
        } catch (e: Exception) {
            println("--------------------------EXCEPTION ON SERIALIZER------------------------------")
            listOf()
        }
    }

    inline fun <reified T> List<T>.toJson(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(this)
    }

    inline fun <reified T> Set<T>.toJson(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(this)
    }
}