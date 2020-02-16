package com.romanidze.studeeper.modules.user.handlers

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileConfirmationService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

/**
 * 16.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class ProfileConfirmationHandler(private val confirmService: ProfileConfirmationService) {

    fun confirmProfile(req: ServerRequest): Mono<ServerResponse>{

        val confirmInput = req.pathVariable("confirm_string")

        return ServerResponse.ok()
                .body(this.confirmService.confirmProfile(confirmInput), MessageResponseDTO::class.java)

    }

}