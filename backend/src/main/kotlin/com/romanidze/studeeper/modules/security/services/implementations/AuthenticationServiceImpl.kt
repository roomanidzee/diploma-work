package com.romanidze.studeeper.modules.security.services.implementations

import com.romanidze.studeeper.modules.security.components.JWTComponent
import com.romanidze.studeeper.modules.security.details.UserDetailsImpl
import com.romanidze.studeeper.modules.security.dto.LoginDTO
import com.romanidze.studeeper.modules.security.dto.LoginResponseDTO
import com.romanidze.studeeper.modules.security.properties.JWTProperties
import com.romanidze.studeeper.modules.security.services.interfaces.AuthenticationService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * 19.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class AuthenticationServiceImpl(
        @Qualifier("customUserDetailsService")
        private val detailsService: ReactiveUserDetailsService,
        private val properties: JWTProperties,
        private val encoder: PasswordEncoder,
        private val jwtComponent: JWTComponent
): AuthenticationService {

    override fun loginUser(loginDTO: LoginDTO): Mono<LoginResponseDTO> {

        val username = loginDTO.username
        val password = loginDTO.password

        val user =
                detailsService.findByUsername(username)
                              .map {
                                  (it as UserDetailsImpl).getUser()
                              }

        val fallback = Mono.error<LoginResponseDTO>(
                IllegalArgumentException("No such user")
        )

        return user.map {

            if(!this.encoder.matches(password, it.password)){
                Mono.empty<LoginResponseDTO>()
            }

            val time = LocalDateTime.now(ZoneId.of("Europe/Moscow"))
                                    .plusSeconds(this.properties.expiresAt)

            val expirationTime = Date.from(time.atZone(ZoneId.of("Europe/Moscow")).toInstant())

            val token = this.jwtComponent.generateToken(it, expirationTime)

            val dateFormat = "dd.MM.yyyy HH:mm:ss"
            val df = SimpleDateFormat(dateFormat)

            LoginResponseDTO(it.username, token, df.format(time))

        }.switchIfEmpty(fallback)

    }
}