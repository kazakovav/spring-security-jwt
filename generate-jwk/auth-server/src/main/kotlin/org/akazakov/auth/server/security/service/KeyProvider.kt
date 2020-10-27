package org.akazakov.auth.server.security.service

import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

interface KeyProvider {
    fun getPrivateKey(): RSAPrivateKey
    fun getPublicKey(): RSAPublicKey
    fun getKeyId(): String
}