package com.akazakov.resource.user

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserInfoController {

    @GetMapping("me")
    fun getCurrentUser(): Jwt {
        val authentication = SecurityContextHolder.getContext().authentication;
        return authentication.principal as Jwt;
    }
}