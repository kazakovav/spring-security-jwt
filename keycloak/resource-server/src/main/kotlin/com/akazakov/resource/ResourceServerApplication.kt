package com.akazakov.resource

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ResourceServerApplication

fun main(args: Array<String>) {
	runApplication<ResourceServerApplication>(*args)
}

@RestController
@RequestMapping("/version")
class VersionController {
	@Value("\${app.version}")
	private lateinit var version: String

	@GetMapping
	fun getVersion(): String = version

}
