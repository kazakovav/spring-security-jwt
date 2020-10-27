package org.akazakov.auth.server.security.api.dto

data class AuthenticationRequest(
        val userName: String,
        val password: String
)