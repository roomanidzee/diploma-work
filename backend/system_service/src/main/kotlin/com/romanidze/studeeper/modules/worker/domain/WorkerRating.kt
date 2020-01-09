package com.romanidze.studeeper.modules.worker.domain

import com.github.pozo.KotlinBuilder

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.util.UUID

/**
 * 09.01.2020
 *
 * Worker Rating Model
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
@Document(collection = "ratings")
@TypeAlias("rating")
data class WorkerRating(

    @Id
    val id: String? = UUID.randomUUID().toString(),

    @Field("profile_id")
    val profileID: String,

    @Field("value")
    val value: Long

)