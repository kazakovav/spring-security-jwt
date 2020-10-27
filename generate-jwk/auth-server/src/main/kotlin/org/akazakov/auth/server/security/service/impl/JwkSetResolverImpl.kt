package org.akazakov.auth.server.security.service.impl

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import org.akazakov.auth.server.security.service.JwkSetResolver
import org.akazakov.auth.server.security.service.KeyProvider
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class JwkSetResolverImpl(private val keyProvider: KeyProvider) : JwkSetResolver {

    private lateinit var jwkSet: JWKSet

    @PostConstruct
    fun afterPropertiesSet(): Unit {
        jwkSet = buildJwkSet()
    }

    private fun buildJwkSet(): JWKSet {
        val builder: RSAKey.Builder = RSAKey.Builder(keyProvider.getPublicKey())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(keyProvider.getKeyId())
        return JWKSet(builder.build())
    }

    override fun getJwkSet(): JWKSet = jwkSet

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val log = LoggerFactory.getLogger(javaClass.enclosingClass)
    }
}