package com.slearning.pokedex.controller

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import com.slearning.pokedex.model.Database
import java.io.File

class DatabaseController(private var connectionInfo: DatabaseConnectionInfo) {
    private var database: Database = Database("{}")

    init {
        connect(connectionInfo)
    }

    fun connect(connectionInfo: DatabaseConnectionInfo): Database {
        database =  if (connectionInfo.useFile) {
            connectToLocalFile(connectionInfo.url)
        } else {
            connectToPostgresDB(connectionInfo.url, connectionInfo.user, connectionInfo.pwd)
        }

        return database
    }

    private fun connectToPostgresDB(url: String, user: String, pwd: String): Database {
        println("[DatabaseController::connectToPostgresDB()]: SQL($url) CONNECTION")

        println("url: $url")
        println("user: $user")
        println("pwd: $pwd")

        return Database("{}")
    }

    private fun connectToLocalFile(url: String): Database {
        println("[DatabaseController::connectToLocalFile()]: FILE($url) CONNECTION")

        var db: Database = Database("{}")

        try{
            val file = File(url)

            if (!file.exists()){
                file.createNewFile()
                file.writeText("{}")
            }

            db = Database(file.readText())
        }
        catch(exception: Exception){
            exception.printStackTrace()
        }

        return db
    }

    inline fun <reified T: Any> getMutableMapOfDeserializedData(): MutableMap<String, T> = getDatabase().getMutableMapOfDeserializedData()

    inline fun <reified T:Any> insertNewDeserializedData(newData: T): Database {
        println("[DatabaseController::insertNewDeserializedData()]: called | '${getConnectionInfo().url}'")

        getDatabase().insertNewDeserializedData(newData)

        if (getConnectionInfo().useFile) {
            try{
                val file = File(getConnectionInfo().url)

                if (!file.exists()){
                    file.createNewFile()
                }

                file.writeText(getDatabase().getStringData())

            }
            catch(exception: Exception){
                exception.printStackTrace()
            }
        }

        return getDatabase()
    }

    fun getDatabase(): Database = database
    fun setDatabase(newDatabase: Database): Database {this.database = newDatabase; return this.database}

    fun getConnectionInfo(): DatabaseConnectionInfo = connectionInfo
    fun setConnectionInfo(newConnectionInfo: DatabaseConnectionInfo): DatabaseController {
        this.connectionInfo = newConnectionInfo
        return this
    }

    fun isUsingFile(): Boolean = connectionInfo.useFile
}