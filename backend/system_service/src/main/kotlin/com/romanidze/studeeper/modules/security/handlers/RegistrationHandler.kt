package com.romanidze.studeeper.modules.security.handlers

import com.romanidze.studeeper.modules.security.dto.RegistrationDTO
import com.romanidze.studeeper.modules.security.enums.Role
import com.romanidze.studeeper.modules.security.services.interfaces.RegistrationService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

/**
 * 19.11.2019
 *
 * Handler for all registration requests
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class RegistrationHandler(private val service: RegistrationService) {

    fun register(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(RegistrationDTO::class.java)
                .flatMap {
                    ServerResponse.ok()
                                  .body(
                                          this.service.register(
                                                  it,
                                                  roles = mutableListOf(Role.USER.toString())
                                          ), RegistrationDTO::class.java
                                  )
                }

    }

}