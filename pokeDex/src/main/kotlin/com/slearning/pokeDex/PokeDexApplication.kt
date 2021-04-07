package com.slearning.pokeDex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication (exclude = [DataSourceAutoConfiguration::class])
@RestController
class PokeDexApplication {
	@GetMapping("/hello")
	fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String? {
		return String.format("Hello %s!", name)
	}
}

fun main(args: Array<String>) {
	runApplication<PokeDexApplication>(*args)
}
