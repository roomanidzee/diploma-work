package com.romanidze.studeeper.modules.employer.senders

import com.fasterxml.jackson.databind.ObjectMapper
import com.romanidze.studeeper.config.kafka.producer.KafkaProducerProperties
import com.romanidze.studeeper.modules.employer.dto.EmployerRequestKafkaRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult
import reactor.kotlin.core.publisher.toMono

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class EmployerRequestSender(
   private val producerConfig: KafkaSender<String, String>,
   private val objectMapper: ObjectMapper,
   private val kafkaProps: KafkaProducerProperties
) {

    fun produceRequest(record: EmployerRequestKafkaRecord): Mono<SenderResult<Int>>{

        return Mono.fromRunnable {
            val payload = this.objectMapper.writeValueAsString(record)

            val kafkaMessage = SenderRecord.create(
                ProducerRecord<String, String>(kafkaProps.topic, payload),
                1
            )

            this.producerConfig.send(kafkaMessage.toMono()).next()

        }

    }

}