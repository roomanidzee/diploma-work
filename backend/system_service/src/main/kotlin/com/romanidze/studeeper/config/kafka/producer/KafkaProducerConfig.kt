package com.romanidze.studeeper.config.kafka.producer

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
class KafkaProducerConfig(private val kafkaProducerProps: KafkaProducerProperties) {

    @Bean
    fun producerProps(): KafkaSender<String, String>{

        val producerConfigProps = mapOf(
           ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
           ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
           ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProducerProps.topic
        )

        val producerOpts: SenderOptions<String, String> = SenderOptions.create(producerConfigProps)

        return KafkaSender.create(producerOpts)

    }

}