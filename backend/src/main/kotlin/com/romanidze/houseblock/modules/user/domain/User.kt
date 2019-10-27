package com.romanidze.houseblock.modules.user.domain

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class User(
  val id: Long?,
  val username: String,
  val password: String,
  val role: String,
  val state: String
)