package com.romanidze.studeeper.modules.security.services.interfaces

import com.romanidze.studeeper.modules.security.dto.RegistrationDTO
import com.romanidze.studeeper.modules.security.dto.RegistrationResponseDTO
import com.romanidze.studeeper.modules.security.enums.Role
import reactor.core.publisher.Mono

/**
 * 19.11.2019
 *
 * Interface for RegistrationService
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface RegistrationService {

    fun register(data: RegistrationDTO, roles: List<String>?): Mono<RegistrationResponseDTO>

}