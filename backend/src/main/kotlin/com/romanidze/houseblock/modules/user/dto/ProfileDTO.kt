package com.romanidze.houseblock.modules.user.dto

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class ProfileDTO(
        val surname: String,
        val name: String,
        val patronymic: String,
        val email: String,
        val phone: String
)