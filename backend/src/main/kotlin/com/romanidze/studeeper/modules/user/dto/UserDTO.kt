package com.romanidze.studeeper.modules.user.dto

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class UserDTO(
        val id: String,
        val username: String
)