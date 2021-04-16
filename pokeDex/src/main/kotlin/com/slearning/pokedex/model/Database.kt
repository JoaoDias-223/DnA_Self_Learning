package com.slearning.pokedex.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Database(private var data: String) {

    inline fun <reified T: Any> getMutableMapOfDeserializedData(): MutableMap<String, T> {
        var mutableMapOfDeserializedData: MutableMap<String, T> = mutableMapOf()

        val string = getStringData()
        mutableMapOfDeserializedData = Json.decodeFromString(getStringData() ?: "{}")

        return mutableMapOfDeserializedData
    }

    inline fun <reified T:Any> insertNewDeserializedData(newData: T): Database {
        val mutableMapOfDeserializedData = getMutableMapOfDeserializedData<T>()
        mutableMapOfDeserializedData["${newData.hashCode()}"] = newData

        setStringData(Json.encodeToString(mutableMapOfDeserializedData))

        return this
    }

    fun getStringData(): String = this.data

    fun setStringData(newData: String): String {
        println("[Database::setStringData()]: called")
        this.data = newData
        return this.data
    }
}