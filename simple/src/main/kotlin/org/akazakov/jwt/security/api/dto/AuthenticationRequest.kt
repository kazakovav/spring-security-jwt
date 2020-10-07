package org.akazakov.jwt.security.api.dto

data class AuthenticationRequest(
        val userName: String,
        val password: String
)