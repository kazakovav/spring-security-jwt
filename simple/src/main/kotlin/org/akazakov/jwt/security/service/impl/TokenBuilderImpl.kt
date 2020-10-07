package org.akazakov.jwt.security.service.impl

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.akazakov.jwt.security.service.KeyProvider
import org.akazakov.jwt.security.service.TokenBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class TokenBuilderImpl(
        @Value("\${jwt.token-validity-in-seconds}")
        private val tokenValidityInSeconds: Long,
        private val keyProvider: KeyProvider
) : TokenBuilder {

    override fun buildToken(authorities: List<String>, userName: String): String {
        val expiration = getExpiration()
        val claims: Map<String, Any> = buildClaims(authorities);
        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setSubject(userName)
                .signWith(getKey(), SignatureAlgorithm.RS256)
                .setExpiration(expiration)
                .compact()
    }

    private fun getExpiration(): Date =
            Date(System.currentTimeMillis() + tokenValidityInSeconds * 1000)

    private fun getKey() = keyProvider.getKey()

    private fun buildClaims(authorities: List<String>): Map<String, Any> {
        val claims = HashMap<String, Any>()
        claims["authorities"] = authorities
        return claims
    }
}