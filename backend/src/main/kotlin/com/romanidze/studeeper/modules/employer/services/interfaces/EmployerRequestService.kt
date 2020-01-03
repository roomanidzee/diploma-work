package com.romanidze.studeeper.modules.employer.services.interfaces

import com.romanidze.studeeper.modules.employer.dto.EmployerRequestDTO
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface EmployerRequestService {

    fun createRequest(request: EmployerRequestDTO): Mono<MessageResponseDTO>
    fun updateRequest(request: EmployerRequestDTO): Mono<EmployerRequestDTO>
    fun getByEmployer(employerID: String): Flux<EmployerRequestDTO>
    fun getByID(id: String): Mono<EmployerRequestDTO>
    fun getByWorker(workerID: String): Flux<EmployerRequestDTO>
    fun getByStatus(status: String): Flux<EmployerRequestDTO>

}