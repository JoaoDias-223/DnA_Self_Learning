package com.slearning.pokedex

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = ["com.slearning.pokedex"])
class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<URLController>(){
                if (args.isNotEmpty() && args[0] == "migrate"){
                    this.setAdditionalProfiles("dbmigration")
                }
            }
        }
    }
}