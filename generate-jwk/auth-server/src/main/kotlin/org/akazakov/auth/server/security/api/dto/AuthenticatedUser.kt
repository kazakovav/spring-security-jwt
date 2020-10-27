package org.akazakov.auth.server.security.api.dto

import java.util.*


data class AuthenticatedUser(
        val id: UUID,
        val userName: String,
        val authorities: List<String>
)
