package com.romanidze.studeeper.modules.user.domain

import com.github.pozo.KotlinBuilder
import com.romanidze.studeeper.modules.security.enums.Role
import com.romanidze.studeeper.modules.security.enums.State

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.util.UUID

/**
 *
 * User entity
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@Document(collection = "users")
@TypeAlias("user")
@KotlinBuilder
data class User(
        @Id
        val id: String? = UUID.randomUUID().toString(),

        @Field("username")
        val username: String,

        @Field("password")
        val password: String?,

        @Field("roles")
        val roles: List<String>? = mutableListOf(
                Role.USER.toString()
        ).toList(),

        @Field("state")
        val state: String? = State.CONFIRMED.toString()


)