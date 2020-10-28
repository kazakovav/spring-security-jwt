package org.akazakov.auth.server.security.service.impl

import org.akazakov.auth.server.security.config.JwtProperties
import org.akazakov.auth.server.security.service.KeyProvider
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.Security
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.annotation.PostConstruct

@Component
class KeyProviderImpl(private val jwtProperties: JwtProperties) : KeyProvider {
    private lateinit var keyPair: KeyPair
    private lateinit var keyId: UUID

    @PostConstruct
    fun afterPropertiesSet(): Unit {
        log.info("Loaded properties: $jwtProperties")
        keyPair = generateKeyPair();
        keyId = UUID.randomUUID();
    }

    private fun generateKeyPair(): KeyPair {
        Security.addProvider(BouncyCastleProvider())
        val keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, "BC")
        keyPairGenerator.initialize(KEY_SIZE, SecureRandom())
        return keyPairGenerator.generateKeyPair()
    }

    override fun getPrivateKey(): RSAPrivateKey = keyPair.private as RSAPrivateKey

    override fun getPublicKey(): RSAPublicKey = keyPair.public as RSAPublicKey

    override fun getKeyId(): String = keyId.toString()

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val log = LoggerFactory.getLogger(javaClass.enclosingClass)

        @JvmStatic
        val ALGORITHM = "RSA"

        @JvmStatic
        val KEY_SIZE = 2048
    }
}