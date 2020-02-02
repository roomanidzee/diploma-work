package com.romanidze.studeeper.modules.worker.dto

import com.github.pozo.KotlinBuilder

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * DTO for working with WorkerRatingAggregated entity
 *
 * 09.01.2020
 * @author Andrey Romanov
 */
@KotlinBuilder
data class WorkerRatingAggregatedDTO(

    @JsonProperty("profile_id")
    val profileID: String,

    val value: Long

)