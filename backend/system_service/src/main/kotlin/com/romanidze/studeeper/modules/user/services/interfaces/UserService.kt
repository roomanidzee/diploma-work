package com.romanidze.studeeper.modules.user.services.interfaces

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.UserDTO

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 *
 * Interface for UserService
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
interface UserService {

    fun getAllUsers(): Flux<UserDTO>
    fun save(userDTO: UserDTO): Mono<MessageResponseDTO>

}