package com.romanidze.studeeper.modules.employer.services.implementations

import com.romanidze.studeeper.modules.employer.dto.EmployerRequestDTO
import com.romanidze.studeeper.modules.employer.dto.EmployerRequestKafkaRecord
import com.romanidze.studeeper.modules.employer.mappers.EmployerRequestMapper
import com.romanidze.studeeper.modules.employer.repositories.interfaces.EmployerRequestRepository
import com.romanidze.studeeper.modules.employer.senders.EmployerRequestSender
import com.romanidze.studeeper.modules.employer.services.interfaces.EmployerRequestService
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class EmployerRequestServiceImpl(
        private val repo: EmployerRequestRepository,
        private val mapper: EmployerRequestMapper,
        private val requestSender: EmployerRequestSender
): EmployerRequestService {

    override fun createRequest(request: EmployerRequestDTO): Mono<MessageResponseDTO> {

        val requestModel = this.mapper.dtoToDomain(request)

        val timeTimestamp = LocalDateTime.now()
                                         .atZone(ZoneId.of("Europe/Moscow"))
                                         .toEpochSecond()
                                         .toDouble()

        val kafkaRecord = EmployerRequestKafkaRecord(
            requestTimestamp = timeTimestamp,
            vacancyID = requestModel.vacancyID,
            employerID = requestModel.employerID,
            workerID = requestModel.workerID,
            requestStatus = requestModel.requestStatus
        )

        val kafkaResult = this.requestSender.produceRequest(kafkaRecord)

        return kafkaResult.flatMap {
            this.repo.save(requestModel)
                    .map {
                        MessageResponseDTO("Employer request saved")
                    }
        }

    }

    override fun updateRequest(request: EmployerRequestDTO): Mono<EmployerRequestDTO> {

        val requestModel = this.mapper.dtoToDomain(request)

        val timeTimestamp = LocalDateTime.now()
                .atZone(ZoneId.of("Europe/Moscow"))
                .toEpochSecond()
                .toDouble()

        val kafkaRecord = EmployerRequestKafkaRecord(
                requestTimestamp = timeTimestamp,
                vacancyID = requestModel.vacancyID,
                employerID = requestModel.employerID,
                workerID = requestModel.workerID,
                requestStatus = requestModel.requestStatus
        )

        val kafkaResult = this.requestSender.produceRequest(kafkaRecord)

        return kafkaResult.flatMap {
            this.repo.update(requestModel)
                    .map {
                        request
                    }
        }

    }

    override fun getByEmployer(employerID: String): Flux<EmployerRequestDTO> {

        return this.repo.findByEmployerID(employerID)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getByID(id: String): Mono<EmployerRequestDTO> {

        return this.repo.findByID(id)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getByWorker(workerID: String): Flux<EmployerRequestDTO> {

        return this.repo.findByWorkerID(workerID)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getByStatus(status: String): Flux<EmployerRequestDTO> {

        return this.repo.findByRequestStatus(status)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }
}