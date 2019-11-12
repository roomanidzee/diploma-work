package com.romanidze.studeeper.modules.user.services.implementations

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.UserDTO
import com.romanidze.studeeper.modules.user.mappers.UserMapper
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository
import com.romanidze.studeeper.modules.user.services.interfaces.UserService

import org.springframework.stereotype.Service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(private val userMapper: UserMapper,
                      private val userRepository: UserRepository): UserService {
    override fun getAllUsers(): Flux<UserDTO> {
        return this.userRepository.findAll()
                .map(this.userMapper::domainToDTO)
    }

    override fun save(userDTO: UserDTO): Mono<MessageResponseDTO> {

        val user = this.userMapper.dtoToDomain(userDTO)

        return this.userRepository.save(user)
                .map { MessageResponseDTO(message="Пользователь сохранён") }
    }
}