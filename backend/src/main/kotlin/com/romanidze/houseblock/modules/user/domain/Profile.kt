package com.romanidze.houseblock.modules.user.domain

import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime

@KotlinBuilder
data class Profile(
  val id: Long?,
  val userID: Long,
  val surname: String,
  val name: String,
  val patronymic: String,
  val email: String,
  val phone: String,
  val createdTime: LocalDateTime = LocalDateTime.now()
)