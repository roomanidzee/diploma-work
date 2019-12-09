package com.romanidze.studeeper.modules.graphods.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime

/**
 * 09.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
data class GraphRecordDTO(

  val specialization: String,
  val graduation: LocalDateTime,

  @JsonProperty("profile_id")
  val profileID: Long
)