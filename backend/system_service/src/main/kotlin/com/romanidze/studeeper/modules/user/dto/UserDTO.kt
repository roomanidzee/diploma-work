package com.romanidze.studeeper.modules.user.dto

import com.github.pozo.KotlinBuilder

/**
 *
 * DTO for working with User entity
 *
 * 17.11.2019
 * @author Andrey Romanov
 *
 */
@KotlinBuilder
data class UserDTO(
        val id: String,
        val username: String
)