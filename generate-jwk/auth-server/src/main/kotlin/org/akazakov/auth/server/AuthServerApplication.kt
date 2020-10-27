package org.akazakov.auth.server

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableConfigurationProperties
class AuthServerApplication

fun main(args: Array<String>) {
	runApplication<AuthServerApplication>(*args)
}

@RestController
@RequestMapping("/version")
class VersionController {
	@Value("\${app.version}")
	private lateinit var version: String

	@GetMapping
	fun getVersion(): String = version

}
