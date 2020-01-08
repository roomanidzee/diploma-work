package com.romanidze.studeeper.modules.files.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.pozo.KotlinBuilder

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@KotlinBuilder
data class FileInfoDTO(

   @JsonProperty("user_id")
   val userID: String,

   val path: String,

   @JsonProperty("type")
   val fileType: String,

   val size: Int

)