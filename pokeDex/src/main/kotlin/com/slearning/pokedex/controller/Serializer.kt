package com.slearning.pokedex.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

object Serializer{
    inline fun <reified T> String.fromJsonToMutableMapOfStringAndObject(): MutableMap<String, T> {
        val mapper = jacksonObjectMapper()

        return try {
            mapper.readValue(this)
        } catch (e: Exception) {
            println("--------------------------EXCEPTION ON SERIALIZER------------------------------")
            mutableMapOf()
        }
    }

    inline fun <reified T> MutableMap<String, T>.toJson(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(this)
    }
}