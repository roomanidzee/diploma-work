package com.romanidze.studeeper.modules.security.handlers

import com.romanidze.studeeper.modules.security.dto.LoginDTO
import com.romanidze.studeeper.modules.security.dto.TokenInfoDTO
import com.romanidze.studeeper.modules.security.services.interfaces.AuthenticationService
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

/**
 * 19.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class AuthenticationHandler(private val service: AuthenticationService) {

    fun authenticate(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(LoginDTO::class.java)
                .flatMap {
                    ServerResponse.ok()
                            .body(this.service.loginUser(it), LoginDTO::class.java)
                }

    }

    fun checkToken(req: ServerRequest): Mono<ServerResponse>{

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap {
                    ServerResponse.ok()
                            .body(this.service.validateToken(it), TokenInfoDTO::class.java)
                }

    }

}