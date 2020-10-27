package org.akazakov.auth.server.security.rest

import org.akazakov.auth.server.security.service.JwkSetResolver
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/.well-known/")
class JwkSetRestController(private val jwkSetResolver: JwkSetResolver) {

    @GetMapping("jwks.json")
    fun getKeys(): MutableMap<String, Any>? {
        return jwkSetResolver.getJwkSet().toJSONObject(true);
    }
}