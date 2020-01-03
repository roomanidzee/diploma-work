package com.romanidze.studeeper.modules.security.filters

import com.romanidze.studeeper.modules.security.converters.JWTAuthenticationConverter
import com.romanidze.studeeper.modules.security.managers.JWTAuthenticationManager
import com.romanidze.studeeper.modules.security.matchers.JWTHeadersMatcher
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler
import org.springframework.stereotype.Component

/**
 * 19.11.2019
 *
 * Filter for JWT data
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class JWTFilter(
        private val manager: JWTAuthenticationManager,
        private val matcher: JWTHeadersMatcher,
        private val converter: JWTAuthenticationConverter
): AuthenticationWebFilter(manager){

    init {
        setServerAuthenticationConverter(converter)
        setRequiresAuthenticationMatcher(matcher)
    }

}