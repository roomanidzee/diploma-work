package com.romanidze.studeeper.modules.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 *
 * Configuration for jwt
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@Component
@ConfigurationProperties("jwt.properties")
class JWTProperties {

    lateinit var header: String
    lateinit var secretKey: String
    var expiresAt: Long = 1000

}