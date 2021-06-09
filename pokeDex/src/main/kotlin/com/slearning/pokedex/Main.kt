package com.slearning.pokedex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = ["com.slearning.pokedex"])
class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<PokemonController>(){
                if (args.isNotEmpty() && args[0] == "migrate"){
                    this.setAdditionalProfiles("dbmigration")
                }
            }
        }
    }
}