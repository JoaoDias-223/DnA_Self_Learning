package com.slearning.pokedex.controller

data class DatabaseConnectionInfo(
    val url: String,
    val user: String,
    val pwd: String,
    val useFile: Boolean
)
