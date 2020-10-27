package org.akazakov.auth.server.security.service

import com.nimbusds.jose.jwk.JWKSet

interface JwkSetResolver {
    fun getJwkSet() : JWKSet
}