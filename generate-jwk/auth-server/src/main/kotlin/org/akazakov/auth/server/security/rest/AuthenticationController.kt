package org.akazakov.auth.server.security.rest

import org.akazakov.auth.server.security.api.exception.UsernameNotFoundException
import org.akazakov.auth.server.security.api.dto.AuthenticationRequest
import org.akazakov.auth.server.security.api.dto.AuthenticationResponse
import org.akazakov.auth.server.security.api.dto.UserRoles
import org.akazakov.auth.server.security.service.TokenBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
class AuthenticationController(
        private val tokenBuilder: TokenBuilder
) {
    @PostMapping("login")
    fun authenticate(@RequestBody authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        if ("admin" != authenticationRequest.userName || "adminPassword" != authenticationRequest.password) {
            throw UsernameNotFoundException("User name not found")
        }
        val userName = authenticationRequest.userName
        val authorities = listOf(UserRoles.USER.name, UserRoles.ADMIN.name)

        val jwt = tokenBuilder.buildToken(authorities, userName)
        return AuthenticationResponse(jwt)
    }
}

