package com.romanidze.studeeper.modules.security.services.implementations

import com.romanidze.studeeper.modules.security.dto.RegistrationDTO
import com.romanidze.studeeper.modules.security.dto.RegistrationResponseDTO
import com.romanidze.studeeper.modules.security.enums.Role
import com.romanidze.studeeper.modules.security.enums.State
import com.romanidze.studeeper.modules.security.services.interfaces.RegistrationService
import com.romanidze.studeeper.modules.user.domain.User
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * 19.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class RegistrationServiceImpl(
        private val encoder: PasswordEncoder,
        private val userRepo: UserRepository
): RegistrationService {
    override fun register(
        data: RegistrationDTO,
        roles: List<String>?
    ): Mono<RegistrationResponseDTO> {

        val newUser = User(
            username = data.username,
            password = this.encoder.encode(data.password),
            roles = roles ?: mutableListOf(Role.USER.toString()),
            state = State.NOT_CONFIRMED.toString()
        )

        return userRepo.save(newUser)
                .map {
                    RegistrationResponseDTO(it.id!!, it.username, it.state!!)
                }

    }
}