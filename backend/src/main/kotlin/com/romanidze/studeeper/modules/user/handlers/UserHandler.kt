package com.romanidze.studeeper.modules.user.handlers

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.UserDTO
import com.romanidze.studeeper.modules.user.services.interfaces.UserService

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

@Component
class UserHandler(private val userService: UserService) {

    fun all(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
                .body(this.userService.getAllUsers(), UserDTO::class.java)
    }

    fun create(req: ServerRequest): Mono<ServerResponse>{
        return req.bodyToMono(UserDTO::class.java)
                  .flatMap {
                    ServerResponse.ok().body(this.userService.save(it), MessageResponseDTO::class.java)
                  }
    }

}