package org.akazakov.jwt.security.service

interface TokenBuilder {
    fun buildToken(authorities: List<String>, userName: String): String
}