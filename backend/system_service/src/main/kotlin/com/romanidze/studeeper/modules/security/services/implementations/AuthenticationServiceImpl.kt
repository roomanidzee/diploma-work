package com.romanidze.studeeper.modules.security.services.implementations

import com.romanidze.studeeper.modules.security.authentication.JWTAuthentication
import com.romanidze.studeeper.modules.security.components.JWTComponent
import com.romanidze.studeeper.modules.security.details.UserDetailsImpl
import com.romanidze.studeeper.modules.security.dto.LoginDTO
import com.romanidze.studeeper.modules.security.dto.LoginResponseDTO
import com.romanidze.studeeper.modules.security.dto.TokenInfoDTO
import com.romanidze.studeeper.modules.security.properties.JWTProperties
import com.romanidze.studeeper.modules.security.services.interfaces.AuthenticationService
import com.romanidze.studeeper.modules.user.domain.User

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

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
        private val jwtComponent: JWTComponent,
        private val redisOperations: ReactiveRedisOperations<String, String>
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


        val generatedResp = user.flatMap {

            if(!this.encoder.matches(password, it.password)){
                Mono.empty<LoginResponseDTO>()
            }

            val time = LocalDateTime.now(ZoneId.of("Europe/Moscow"))
                                    .plusSeconds(this.properties.expiresAt)

            val expirationTime = Date.from(time.atZone(ZoneId.of("Europe/Moscow")).toInstant())

            val token = this.jwtComponent.generateToken(it, expirationTime)

            this.redisOperations.opsForValue()
                               .set(token, it.id!!)

            val dateFormat = "dd.MM.yyyy HH:mm:ss"
            val df = SimpleDateFormat(dateFormat)

            LoginResponseDTO(it.username, token, df.format(expirationTime)).toMono()

        }.switchIfEmpty(fallback)

        val tokenSave = generatedResp.flatMap {
            this.redisOperations.opsForValue()
                                .set(it.token, it.username)
        }

        return tokenSave.flatMap {

            if(!it){
                Mono.error<LoginResponseDTO>(
                   IllegalArgumentException("Didn't save authorized user's token to Redis")
                )
            }

            generatedResp

        }

    }

    override fun validateToken(authentication: Authentication): Mono<TokenInfoDTO> {

        val tokenAuth = authentication as JWTAuthentication

        var expired: Boolean

        try{

            val body: Claims = this.jwtComponent.retrieveToken(tokenAuth)
            body.subject

            expired = false

        }catch(e: Exception){
            when(e){
                is ExpiredJwtException -> {
                    expired = true
                }
                else ->{
                    throw AuthenticationServiceException("Error while working with token: $e")
                }
            }
        }

        return TokenInfoDTO(expired, tokenAuth.name).toMono()

    }

    override fun getUserByAuthentication(authentication: Authentication): Mono<User> {

        val tokenAuth = authentication as JWTAuthentication

        val body: Claims = this.jwtComponent.retrieveToken(tokenAuth)
        val username = body["username"].toString()

        return detailsService.findByUsername(username).flatMap {
            (it as UserDetailsImpl).getUser().toMono()
        }

    }
}