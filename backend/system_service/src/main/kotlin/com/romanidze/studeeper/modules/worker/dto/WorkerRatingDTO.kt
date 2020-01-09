package com.romanidze.studeeper.modules.worker.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder

/**
 *
 * DTO for working with WorkerRating entity
 *
 * 09.01.2020
 * @author Andrey Romanov
 */
@KotlinBuilder
data class WorkerRatingDTO(

    @JsonProperty("profile_id")
    val profileID: String,

    val value: Long

)