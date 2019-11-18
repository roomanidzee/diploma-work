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

data class TokenResponseDTO(
    val token: String,

    @JsonProperty("request_time")
    val requestTime: String
)