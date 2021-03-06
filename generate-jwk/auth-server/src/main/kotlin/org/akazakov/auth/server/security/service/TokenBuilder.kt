package org.akazakov.auth.server.security.service

interface TokenBuilder {
    fun buildToken(authorities: List<String>, userName: String): String
}