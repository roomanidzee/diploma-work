package com.romanidze.studeeper.modules.user.dto

import com.github.pozo.KotlinBuilder

/**
 *
 * DTO for working with Profile entity
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@KotlinBuilder
data class ProfileDTO(

  val surname: String,
  val name: String,
  val patronymic: String

)