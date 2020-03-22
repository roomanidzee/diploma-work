package com.romanidze.studeeper.modules.facility.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
data class FacilityKafkaRecord(

    @JsonProperty("timestamp")
    val recordTimestamp: Float,

    val username: String,

    @JsonProperty("raw_password")
    val rawPassword: String,

    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,

    @JsonProperty("facility_title")
    val facilityTitle: String,

    @JsonProperty("facility_graduation")
    val facilityGraduation: String,

    @JsonProperty("facility_speciality")
    val facilitySpeciality: String,

    @JsonProperty("facility_group")
    val facilityGroup: String

)