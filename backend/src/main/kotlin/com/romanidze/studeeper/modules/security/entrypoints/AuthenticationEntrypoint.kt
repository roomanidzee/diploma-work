package com.romanidze.studeeper.modules.security.entrypoints

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.web.server.ServerWebExchange

import reactor.core.publisher.Mono

/**
 * 18.11.2019
 *
 * Entrypoint for non-authorized
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
class AuthenticationEntrypoint: ServerAuthenticationEntryPoint {

    override fun commence(exchange: ServerWebExchange?, e: AuthenticationException?): Mono<Void> {

        return Mono.fromRunnable{
            exchange!!.response.statusCode = HttpStatus.UNAUTHORIZED
        }

    }
}