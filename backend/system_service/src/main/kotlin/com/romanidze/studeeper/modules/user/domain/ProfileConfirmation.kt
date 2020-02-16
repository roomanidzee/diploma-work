package com.romanidze.studeeper.modules.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.util.UUID

/**
 * 16.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Document(collection = "profile_confirmations")
@TypeAlias("profile_confirmation")
data class ProfileConfirmation(

    @Id
    val id: String? = UUID.randomUUID().toString(),

    @Field("profile_id")
    val profileID: String,

    @Field("confirmation_string")
    val confirmString: String

)