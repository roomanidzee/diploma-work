package com.romanidze.studeeper.modules.facility.listeners

import com.fasterxml.jackson.databind.ObjectMapper
import com.romanidze.studeeper.modules.facility.dto.FacilityKafkaRecord
import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import com.romanidze.studeeper.modules.graphods.repositories.interfaces.FacilityRecordRepository
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.services.interfaces.FacilityRecordService
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import com.romanidze.studeeper.modules.security.dto.RegistrationDTO
import com.romanidze.studeeper.modules.security.dto.RegistrationResponseDTO
import com.romanidze.studeeper.modules.security.enums.Role
import com.romanidze.studeeper.modules.security.services.interfaces.RegistrationService
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import reactor.kafka.receiver.KafkaReceiver
import javax.annotation.PostConstruct

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * 22.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class FacilitySourceListener(
    private val facilityService: FacilityRecordService,
    private val graphRecordService: GraphRecordService,
    private val registrationService: RegistrationService,
    private val profileService: ProfileService,
    private val consumerConfig: KafkaReceiver<String, String>,
    private val objectMapper: ObjectMapper,
    private val passwordEncoder: PasswordEncoder,
    private val facilityRecordRepo: FacilityRecordRepository
) {

    @PostConstruct
    fun init() {

        this.consumerConfig.receive()
                .doOnNext{

                    val record: FacilityKafkaRecord = this.objectMapper.readValue(
                            it.value() as String,
                            FacilityKafkaRecord::class.java
                    )

                    val regResult: Mono<RegistrationResponseDTO> = this.registrationService.register(
                        RegistrationDTO(
                            record.username,
                            this.passwordEncoder.encode(record.rawPassword)
                        ),
                        mutableListOf(Role.USER.toString(), Role.WORKER.toString())
                    )

                    val facilityResult: Mono<FacilityRecordDTO> = regResult.flatMap {

                        this.profileService.save(
                            ProfileDTO(
                                userID = it.id,
                                surname = record.surname,
                                name = record.name,
                                patronymic = record.patronymic,
                                email = record.email
                            )
                        )

                    }.flatMap {

                        this.facilityService.createFacility(
                            FacilityRecordDTO(
                                record.facilityTitle,
                                record.facilityGraduation,
                                record.facilitySpeciality
                            )
                        )

                    }

                    val facilityDBResult = facilityResult.flatMap {
                        this.facilityRecordRepo.findByTitle(it.title).toMono()
                    }


                    regResult.zipWith(facilityDBResult)
                            .flatMap { tupleInst ->

                                this.graphRecordService.createGraphRecord(
                                    GraphRecordDTO(
                                        tupleInst.t2.id!!,
                                        tupleInst.t1.id,
                                        mutableSetOf("need_to_fill")
                                    )
                                )

                            }.subscribe()

                }.subscribe()

    }

}