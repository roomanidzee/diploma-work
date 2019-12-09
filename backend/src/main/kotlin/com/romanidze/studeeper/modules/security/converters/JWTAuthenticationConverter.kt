package com.romanidze.studeeper.modules.security.converters

import com.romanidze.studeeper.modules.security.authentication.JWTAuthentication
import com.romanidze.studeeper.modules.security.properties.JWTProperties
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * 18.11.2019
 *
 * Convert data in headers to authentication
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class JWTAuthenticationConverter(
        private val properties: JWTProperties
): ServerAuthenticationConverter {

    override fun convert(t: ServerWebExchange): Mono<Authentication> {

        val request = t.request
        val authentication: JWTAuthentication?
        var token: String? = null

        val bearerHeader: String? = request.headers.getFirst(properties.header)

        if(bearerHeader != null && bearerHeader.startsWith("${properties.prefix} ")){
            token = bearerHeader.substring(7)
        }

        when(token){
            null -> {

                authentication = JWTAuthentication(null)
                authentication.isAuthenticated = false

            }
            else -> {
                authentication = JWTAuthentication(token)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        return authentication.toMono()

    }

}