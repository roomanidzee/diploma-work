package com.romanidze.studeeper.modules.user.domain

import com.github.pozo.KotlinBuilder

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.util.UUID

/**
 *
 * Profile entity
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@Document(collection = "profiles")
@TypeAlias("profile")
@KotlinBuilder
data class Profile(
        @Id
        val id: String? = UUID.randomUUID().toString(),

        @Field("user_id")
        val userID: String?,

        @Field("surname")
        val surname: String,

        @Field("name")
        val name: String,

        @Field("patronymic")
        val patronymic: String,

        @Field("email")
        val email: String?
)