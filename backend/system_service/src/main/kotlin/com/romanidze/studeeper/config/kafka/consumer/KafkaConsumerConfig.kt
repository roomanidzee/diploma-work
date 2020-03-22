package com.romanidze.studeeper.config.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Configuration
class KafkaConsumerConfig(private val kafkaConsumerProps: KafkaConsumerProperties) {

    @Bean
    fun consumerProps(): KafkaReceiver<Any, Any> {

        val consumerConfigProps = mapOf(
           ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
           ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
           ConsumerConfig.CLIENT_ID_CONFIG to kafkaConsumerProps.clientID,
           ConsumerConfig.GROUP_ID_CONFIG to kafkaConsumerProps.groupID,
           ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaConsumerProps.bootstrapServers
        )

        val consumerOptions =
                ReceiverOptions.create<Any, Any>(consumerConfigProps)
                               .subscription(mutableListOf(kafkaConsumerProps.topic))

        return KafkaReceiver.create(consumerOptions)

    }

}