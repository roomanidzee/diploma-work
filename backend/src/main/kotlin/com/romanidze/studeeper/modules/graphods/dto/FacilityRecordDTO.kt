package com.romanidze.studeeper.modules.graphods.dto

import com.github.pozo.KotlinBuilder

/**
 *
 * DTO for working with FacilityRecord entity
 *
 * 01.01.2020
 * @author Andrey Romanov
 */
@KotlinBuilder
data class FacilityRecordDTO(
    val title: String,
    val graduation: String,
    val speciality: String
)
