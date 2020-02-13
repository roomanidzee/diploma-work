package com.romanidze.studeeper.modules.email.dto

/**
 * 13.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
data class MailDTO(
    val from: String,
    val to: String,
    val subject: String,
    val message: String
)