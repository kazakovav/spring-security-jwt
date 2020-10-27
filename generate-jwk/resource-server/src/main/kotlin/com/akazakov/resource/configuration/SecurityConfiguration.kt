package com.akazakov.resource.configuration

import com.akazakov.resource.security.impl.CustomJwtRolesConverter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(private val objectMapper: ObjectMapper
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http {
            csrf {
                disable()
            }
            authorizeRequests {
                authorize("/version", permitAll)
                authorize(anyRequest, authenticated)
            }

            oauth2ResourceServer {
                jwt {
                    jwtAuthenticationConverter = jwtCustomAuthenticationConverter()
                }
            }
        }
    }

    @Bean
    fun jwtCustomAuthenticationConverter(): Converter<Jwt, AbstractAuthenticationToken>? {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter())
        return jwtAuthenticationConverter
    }

    @Bean
    fun jwtGrantedAuthoritiesConverter(): Converter<Jwt, Collection<GrantedAuthority>?>? {
        return CustomJwtRolesConverter()
    }

}