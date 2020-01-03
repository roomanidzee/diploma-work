package com.romanidze.studeeper.modules.employer.domain

import com.github.pozo.KotlinBuilder
import com.romanidze.studeeper.modules.employer.enums.VacancyStatus
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.UUID

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Document(collection = "vacancies")
@TypeAlias("vacancy")
@KotlinBuilder
data class EmployerRequest(

     @Id
     val id: String? = UUID.randomUUID().toString(),

     @Field("vacancy_id")
     val vacancyID: String,

     @Field("employer_id")
     val employerID: String,

     @Field("worker_id")
     val workerID: String,

     @Field("request_status")
     val requestStatus: String = VacancyStatus.PROCESSING.toString()

)