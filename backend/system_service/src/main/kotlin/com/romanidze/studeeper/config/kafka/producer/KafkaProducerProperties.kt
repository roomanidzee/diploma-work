package com.romanidze.studeeper.config.kafka.producer

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
@ConfigurationProperties("spring.kafka.producer")
class KafkaProducerProperties {

    lateinit var bootstrapServers: String
    lateinit var topic: String

}