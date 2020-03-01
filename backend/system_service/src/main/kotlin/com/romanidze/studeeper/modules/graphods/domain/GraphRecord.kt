package com.romanidze.studeeper.modules.graphods.domain

import com.github.pozo.KotlinBuilder

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.util.UUID

/**
 * 01.01.2020
 *
 * Graph Record Model
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
@Document(collection = "graph_records")
@TypeAlias("graph_record")
data class GraphRecord(

    @Id
    val id: String? = UUID.randomUUID().toString(),

    @Field("facility_id")
    val facilityID: String,

    @Field("profile_id")
    val profileID: String,

    @Field("groupmates")
    val groupmates: Set<String>?

)
