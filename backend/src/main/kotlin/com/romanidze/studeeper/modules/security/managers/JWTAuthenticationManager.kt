package com.romanidze.studeeper.modules.security.managers

import com.romanidze.studeeper.modules.security.authentication.JWTAuthentication
import com.romanidze.studeeper.modules.security.components.JWTComponent
import com.romanidze.studeeper.modules.security.details.UserDetailsImpl
import io.jsonwebtoken.Claims
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * 19.11.2019
 *
 * Manager for user authentication
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class JWTAuthenticationManager(private val jwtComponent: JWTComponent): ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication?): Mono<Authentication> {

        val tokenAuthentication: JWTAuthentication = authentication as JWTAuthentication

        val body: Claims = this.jwtComponent.retrieveToken(tokenAuthentication)

        val userDetails: UserDetails = UserDetailsImpl(
                id = body["sub"].toString(),
                roles = body["roles"] as List<String>,
                state = body["state"].toString(),
                username = body["username"].toString()
        )

        tokenAuthentication.setUserDetails(userDetails)
        tokenAuthentication.isAuthenticated = true

        return tokenAuthentication.toMono()

    }
}