package com.romanidze.studeeper.modules.graphods.domain

import com.github.pozo.KotlinBuilder

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.time.LocalDateTime
import java.util.UUID

/**
 * 01.01.2020
 *
 * Facility Graph Model
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
@Document(collection = "facilities")
@TypeAlias("facility")
data class FacilityRecord (

        @Id
        val id: String? = UUID.randomUUID().toString(),

        @Field("title")
        val title: String,

        @Field("graduation")
        val graduation: LocalDateTime,

        @Field("speciality")
        val speciality: String
)