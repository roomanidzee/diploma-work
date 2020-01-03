package com.romanidze.studeeper.modules.employer.services.implementations

import com.romanidze.studeeper.modules.employer.dto.EmployerRequestDTO
import com.romanidze.studeeper.modules.employer.mappers.EmployerRequestMapper
import com.romanidze.studeeper.modules.employer.repositories.interfaces.EmployerRequestRepository
import com.romanidze.studeeper.modules.employer.services.interfaces.EmployerRequestService
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class EmployerRequestServiceImpl(
        private val repo: EmployerRequestRepository,
        private val mapper: EmployerRequestMapper
): EmployerRequestService {

    override fun createRequest(request: EmployerRequestDTO): Mono<MessageResponseDTO> {

        val requestModel = this.mapper.dtoToDomain(request)

        return this.repo.save(requestModel)
                .map {
                    MessageResponseDTO("Employer request saved")
                }

    }

    override fun updateRequest(request: EmployerRequestDTO): Mono<EmployerRequestDTO> {

        val requestModel = this.mapper.dtoToDomain(request)

        return this.repo.update(requestModel)
                .map {
                    request
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