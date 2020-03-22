package com.romanidze.studeeper.modules.employer.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
data class EmployerRequestKafkaRecord(

   @JsonProperty("timestamp")
   val requestTimestamp: Double,

   @JsonProperty("vacancy_id")
   val vacancyID: String,

   @JsonProperty("employer_id")
   val employerID: String,

   @JsonProperty("worker_id")
   val workerID: String,

   @JsonProperty("request_status")
   val requestStatus: String

)