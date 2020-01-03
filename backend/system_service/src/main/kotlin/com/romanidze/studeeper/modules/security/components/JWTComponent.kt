package com.romanidze.studeeper.modules.security.components

import com.romanidze.studeeper.modules.security.authentication.JWTAuthentication
import com.romanidze.studeeper.modules.security.properties.JWTProperties
import com.romanidze.studeeper.modules.user.domain.User

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.stereotype.Component

import java.util.Date

/**
 * 18.11.2019
 *
 * Component for work with JWT
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class JWTComponent(private val jwtProperties: JWTProperties) {

    fun retrieveToken(authentication: JWTAuthentication): Claims {

        val body: Claims

        try{

            body = Jwts.parser()
                    .setSigningKey(this.jwtProperties.secretKey)
                    .parseClaimsJws(authentication.name)
                    .body

        }catch(e: Exception){

            when(e){
                is MalformedJwtException -> {
                    throw AuthenticationServiceException("JWT - токен сформирован неверно по причине: $e")
                }
                else ->{
                    throw AuthenticationServiceException("При работе с JWT - токеном произошла ошибка: $e")
                }
            }

        }

        return body

    }

    fun generateToken(user: User, expirationTime: Date): String{

        return Jwts.builder()
                .setSubject(user.id)
                .setExpiration(expirationTime)
                .claim("roles", user.roles)
                .claim("state", user.state)
                .claim("username", user.username)
                .signWith(SignatureAlgorithm.HS512, this.jwtProperties.secretKey)
                .compact()

    }

}