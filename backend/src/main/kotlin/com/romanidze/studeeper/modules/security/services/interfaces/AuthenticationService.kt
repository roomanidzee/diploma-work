package com.romanidze.studeeper.modules.security.services.interfaces

import com.romanidze.studeeper.modules.security.dto.LoginDTO
import com.romanidze.studeeper.modules.security.dto.LoginResponseDTO
import com.romanidze.studeeper.modules.security.dto.TokenInfoDTO
import com.romanidze.studeeper.modules.user.domain.User
import org.springframework.security.core.Authentication
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
    fun validateToken(authentication: Authentication): Mono<TokenInfoDTO>
    fun getUserByAuthentication(authentication: Authentication): Mono<User>

}