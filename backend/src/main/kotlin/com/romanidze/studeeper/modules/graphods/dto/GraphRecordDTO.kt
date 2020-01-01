package com.romanidze.studeeper.modules.graphods.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder

/**
 *
 * DTO for working with GraphRecord entity
 *
 * 01.01.2020
 * @author Andrey Romanov
 */
@KotlinBuilder
data class GraphRecordDTO(

    @JsonProperty("facility_id")
    val facilityID: String,

    @JsonProperty("profile_id")
    val profileID: String,

    val groupmates: Set<String>

)