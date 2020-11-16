package com.akazakov.resource.security.impl

import net.minidev.json.JSONArray
import net.minidev.json.JSONObject
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import java.util.stream.Collectors

class KeycloakRealmRolesConverter : Converter<Jwt, Collection<GrantedAuthority>?> {
    private val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val grantedAuthorities: MutableCollection<GrantedAuthority> = jwtGrantedAuthoritiesConverter.convert(jwt)!!
        grantedAuthorities.addAll(extractRealmAuthorities(jwt))
        return grantedAuthorities
    }

    private fun extractRealmAuthorities(jwt: Jwt): Collection<GrantedAuthority> {
        return jwt.getClaim<JSONObject?>(REALM_ACCESS_KEY)?.let { realmAccess ->
            realmAccess[ROLES_KEY]?.let {
                val roles = it as JSONArray
                roles.stream().map { role: Any -> SimpleGrantedAuthority(ROLE_PREFIX + role) }.collect(Collectors.toList())
            }
        } ?: emptyList()
    }

    companion object {
        const val REALM_ACCESS_KEY = "realm_access"
        const val ROLES_KEY = "roles"
        const val ROLE_PREFIX = "ROLE_"
    }
}