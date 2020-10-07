package org.akazakov.jwt.security.service.impl

import org.akazakov.jwt.security.config.JwtProperties
import org.akazakov.jwt.security.service.KeyProvider
import org.bouncycastle.util.io.pem.PemReader
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.InputStreamReader
import java.security.Key
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import javax.annotation.PostConstruct

@Component
class KeyProviderImpl(private val jwtProperties: JwtProperties) : KeyProvider {

    private lateinit var privateKey: Key

    @PostConstruct
    fun afterPropertiesSet(): Unit {
        log.info("Loaded properties: $jwtProperties")
        privateKey = loadKey()
    }

    private fun loadKey(): Key {
        val factory: KeyFactory = KeyFactory.getInstance(ALGORITHM)
        val resource = ClassPathResource(jwtProperties.pemFilePath)

        InputStreamReader(resource.inputStream).use { keyReader ->
            PemReader(keyReader).use { pemReader ->
                val pemObject = pemReader.readPemObject()
                val content = pemObject.content
                val privateKeySpec = PKCS8EncodedKeySpec(content)
                return factory.generatePrivate(privateKeySpec) as RSAPrivateKey
            }
        }
    }

    override fun getKey(): Key = privateKey

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val log = LoggerFactory.getLogger(javaClass.enclosingClass)

        @JvmStatic
        val ALGORITHM = "RSA"
    }
}