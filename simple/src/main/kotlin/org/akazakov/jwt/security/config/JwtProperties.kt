package org.akazakov.jwt.security.config

import org.slf4j.LoggerFactory.getLogger
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct
import kotlin.properties.Delegates

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
    var tokenValidityInSeconds by Delegates.notNull<Long>()

    lateinit var pemFilePath: String

    @PostConstruct
    fun postConstruct() {
        log.info("Jwt properties: $this")
    }

    override fun toString(): String {
        return "[tokenValidityInSeconds: $tokenValidityInSeconds, pemFilePath: $pemFilePath]"
    }

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val log = getLogger(javaClass.enclosingClass)
    }
}