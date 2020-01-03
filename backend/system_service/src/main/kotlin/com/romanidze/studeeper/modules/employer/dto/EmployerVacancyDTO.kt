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
data class EmployerVacancyDTO(

     @JsonProperty("employer_id")
     val employerID: String,

     val description: String,
     val tags: Set<String>

)