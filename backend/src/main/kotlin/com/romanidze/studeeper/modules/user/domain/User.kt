package com.romanidze.studeeper.modules.user.domain

import com.github.pozo.KotlinBuilder

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.util.UUID

@Document(collection = "users")
@TypeAlias("user")
@KotlinBuilder
data class User(
        @Id
        val id: String? = UUID.randomUUID().toString(),

        @Field("username")
        val username: String,

        @Field("password")
        val password: String
)