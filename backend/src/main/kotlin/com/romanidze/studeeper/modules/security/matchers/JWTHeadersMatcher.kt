package com.romanidze.studeeper.modules.security.matchers

import com.romanidze.studeeper.modules.security.properties.JWTProperties
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.core.publisher.switchIfEmpty
import reactor.core.publisher.toMono

/**
 * 19.11.2019
 *
 * Checker for authorization header
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class JWTHeadersMatcher(private val properties: JWTProperties): ServerWebExchangeMatcher {

    override fun matches(exchange: ServerWebExchange?): Mono<ServerWebExchangeMatcher.MatchResult> {

        val request = exchange!!.toMono().map{
            it.request
        }

        return request.map {
            it.headers
        }.filter{
            it.containsKey(properties.header)
        }.flatMap {
            ServerWebExchangeMatcher.MatchResult.match()
        }.switchIfEmpty {
            ServerWebExchangeMatcher.MatchResult.notMatch()
        }


    }
}