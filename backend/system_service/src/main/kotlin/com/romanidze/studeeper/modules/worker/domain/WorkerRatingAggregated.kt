package com.romanidze.studeeper.modules.worker.domain

import com.github.pozo.KotlinBuilder

import org.springframework.data.mongodb.core.mapping.Field

/**
 * 02.02.2020
 *
 * Worker Rating Aggregated Model
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
data class WorkerRatingAggregated(
    
    @Field("profile_id")
    val profileID: String,

    val value: Long
)