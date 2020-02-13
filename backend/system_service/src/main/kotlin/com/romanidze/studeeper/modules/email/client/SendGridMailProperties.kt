package com.romanidze.studeeper.modules.email.client

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * 13.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
@ConfigurationProperties("sendgrid.config")
class SendGridMailProperties{

    lateinit var baseURL: String
    lateinit var apiKey: String
    lateinit var mailEndpoint: String

}