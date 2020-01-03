package com.romanidze.studeeper.modules.employer.domain

import com.github.pozo.KotlinBuilder
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.UUID

/**
 * 03.01.2020
 *
 * Employer Vacancy Model
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Document(collection = "vacancies")
@TypeAlias("vacancy")
@KotlinBuilder
data class EmployerVacancy(

     @Id
     val id: String? = UUID.randomUUID().toString(),

     @Field("employer_id")
     val employerID: String,

     @Field("description")
     val description: String,

     @Field("tags")
     val tags: Set<String>

)