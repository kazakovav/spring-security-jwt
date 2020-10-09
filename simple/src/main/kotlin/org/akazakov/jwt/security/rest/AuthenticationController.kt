package org.akazakov.jwt.security.rest

import org.akazakov.jwt.security.api.dto.AuthenticationRequest
import org.akazakov.jwt.security.api.dto.AuthenticationResponse
import org.akazakov.jwt.security.api.dto.UserRoles
import org.akazakov.jwt.security.service.TokenBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/")
class AuthenticationController(
        private val tokenBuilder: TokenBuilder
) {

    @GetMapping("me")
    fun getCurrentUser(): Jwt {
        val authentication = SecurityContextHolder.getContext().authentication;
        return authentication.principal as Jwt;
    }

    @PostMapping("/login")
    fun authenticate(@RequestBody authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        if ("admin" != authenticationRequest.userName && "adminPassword" != authenticationRequest.password) {
            throw UsernameNotFoundException("User name not found")
        }
        val userName = authenticationRequest.userName
        val authorities = listOf(UserRoles.USER.name, UserRoles.ADMIN.name)

        val jwt = tokenBuilder.buildToken(authorities, userName)
        return AuthenticationResponse(jwt)
    }


}