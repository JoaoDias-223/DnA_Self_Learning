package com.slearning.pokedex

import org.springframework.boot.runApplication

class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<URLController>(*args) // *: spread operator (same as ... in JS)
        }
    }
}