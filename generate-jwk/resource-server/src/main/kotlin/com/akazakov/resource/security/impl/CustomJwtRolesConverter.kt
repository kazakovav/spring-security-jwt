package com.akazakov.resource.security.impl

import net.minidev.json.JSONArray
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import java.util.stream.Collectors

class CustomJwtRolesConverter : Converter<Jwt, Collection<GrantedAuthority>?> {

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val authorities = jwt.claims[AUTHORITIES_KEY] as JSONArray

        return authorities.stream().map { role: Any ->
            SimpleGrantedAuthority(ROLE_PREFIX + role)
        }.collect(Collectors.toList())
    }

    companion object {
        const val AUTHORITIES_KEY = "authorities"
        const val ROLE_PREFIX = "ROLE_"
    }
}