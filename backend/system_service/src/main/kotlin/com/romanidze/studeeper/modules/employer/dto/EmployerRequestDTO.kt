package com.romanidze.studeeper.modules.employer.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
data class EmployerRequestDTO(

    val id: String,

    @JsonProperty("vacancy_id")
    val vacancyID: String,

    @JsonProperty("employer_id")
    val employerID: String,

    @JsonProperty("worker_id")
    val workerID: String,

    @JsonProperty("request_status")
    val requestStatus: String

)