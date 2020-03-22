package com.romanidze.studeeper.config.kafka.consumer

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
@ConfigurationProperties("spring.kafka.consumer")
class KafkaConsumerProperties {

    lateinit var bootstrapServers: String
    lateinit var groupID: String
    lateinit var clientID: String
    lateinit var topic: String

}