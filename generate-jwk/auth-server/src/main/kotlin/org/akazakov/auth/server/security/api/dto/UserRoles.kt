package org.akazakov.auth.server.security.api.dto

enum class UserRoles {
    USER, ADMIN;

    fun toRoleName(): String = ROLE_PREFIX + this.name

    companion object {
        const val ROLE_PREFIX = "ROLE_"
    }
}