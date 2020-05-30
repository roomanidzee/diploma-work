package com.romanidze.studeeper.modules.security.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 18.11.2019
 *
 * DTO for work with user
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */

data class LoginDTO(
    val username: String,
    val password: String
)

data class RegistrationDTO(
    val username: String,
    val password: String
)

data class RegistrationResponseDTO(
    val id: String,
    val username: String,
    val state: String
)

data class TokenInfoDTO(
    val expired: Boolean,
    val token: String
)

data class LoginResponseDTO(

    val username: String,
    val token: String,
    val roles: List<String>,

    @JsonProperty("expiration_time")
    val expirationTime: String
)