package com.romanidze.studeeper.modules.security.services.interfaces

import com.romanidze.studeeper.modules.security.dto.LoginDTO
import com.romanidze.studeeper.modules.security.dto.LoginResponseDTO
import reactor.core.publisher.Mono

/**
 * 19.11.2019
 *
 * Interface for AuthenticationService
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface AuthenticationService {
    fun loginUser(loginDTO: LoginDTO): Mono<LoginResponseDTO>

}