package com.slearning.pokedex.controller

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File

class DatabaseController(url: String, user: String, pwd: String, useFile: Boolean=false) {
    private var connection: Any? = null

    private var url: String = ""
    private var user: String = ""
    private var pwd: String = ""
    private var useFile: Boolean = false

    init {
        this.url = url
        this.user = user
        this.pwd = pwd
        this.useFile = useFile

        if (useFile)
            this.connection = connect(url=url, user=user, pwd=pwd, file=useFile) as File?
        else {
            this.connection = connect(url=url, user=user, pwd=pwd, file=useFile)
        }
    }

    private fun connect(url: String, user: String, pwd: String, file: Boolean): Any? {
        return if (file) {
            connectToLocalFile(url)
        } else {
            connectToPostgresDB(url, user, pwd)
        }
    }

    private fun connectToPostgresDB(url: String, user: String, pwd: String): Any {
        println("SQL($url) CONNECTION")

        println("url: $url")
        println("user: $user")
        println("pwd: $pwd")

        return Any()
    }

    private fun connectToLocalFile(url: String): File? {
        println("FILE($url) CONNECTION")

        var file: File? = null

        try{
            file = File(url)

            if (!file.exists()){
                file.createNewFile()
            }
        }
        catch(exception: Exception){
            exception.printStackTrace()
        }

        return file
    }

    fun retryConnection() {
        this.connection = connect(url=this.url, user=user, pwd=pwd, file=useFile) as File?
    }

    inline fun <reified T: Any> getMutableMapOfDeserializedData(): MutableMap<String, T> {
        var data: MutableMap<String, T> = mutableMapOf()

        if (this.isUsingFile()) {
            val fileStringData = (getConnection() as File?)?.readText() ?: ""
            println(fileStringData)
            data = Json.decodeFromString(fileStringData)
        }

        return data
    }

    inline fun <reified T:Any> insertNewDeserializedData(newData: T): MutableMap<String, T> {
        val mutableMapOfDeserializedData = getMutableMapOfDeserializedData<T>()
        mutableMapOfDeserializedData["${newData.hashCode()}"] = newData

        if (this.isUsingFile()) {
            (getConnection() as File?)?.writeText(Json.encodeToString(mutableMapOfDeserializedData))
        }

        return mutableMapOfDeserializedData
    }


    fun getConnection(): Any? = connection

    fun isUsingFile(): Boolean = useFile
}