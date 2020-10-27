package org.akazakov.auth.server.security.api.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class UsernameNotFoundException(message: String) : RuntimeException(message) {
}