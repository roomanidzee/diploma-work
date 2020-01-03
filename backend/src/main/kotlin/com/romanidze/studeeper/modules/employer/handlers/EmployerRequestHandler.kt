package com.romanidze.studeeper.modules.employer.handlers

import com.romanidze.studeeper.modules.employer.dto.EmployerRequestDTO
import com.romanidze.studeeper.modules.employer.services.interfaces.EmployerRequestService
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class EmployerRequestHandler(private val service: EmployerRequestService) {

    fun createRequest(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(EmployerRequestDTO::class.java)
                .flatMap {
                    ServerResponse.ok()
                            .body(this.service.createRequest(it), MessageResponseDTO::class.java)
                }

    }

    fun updateRequest(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(EmployerRequestDTO::class.java)
                .flatMap {
                    ServerResponse.ok()
                            .body(this.service.updateRequest(it), EmployerRequestDTO::class.java)
                }

    }

    fun getByEmployer(req: ServerRequest): Mono<ServerResponse>{

        val employerID = req.pathVariable("employer_id")

        return ServerResponse.ok()
                             .body(this.service.getByEmployer(employerID), EmployerRequestDTO::class.java).toMono()

    }

    fun getByID(req: ServerRequest): Mono<ServerResponse>{

        val requestID = req.pathVariable("id")

        return ServerResponse.ok()
                .body(this.service.getByID(requestID), EmployerRequestDTO::class.java).toMono()

    }

    fun getByWorker(req: ServerRequest): Mono<ServerResponse>{

        val workerID = req.pathVariable("worker_id")

        return ServerResponse.ok()
                .body(this.service.getByWorker(workerID), EmployerRequestDTO::class.java).toMono()

    }

    fun getByStatus(req: ServerRequest): Mono<ServerResponse>{

        val status = req.queryParam("request_status").get()

        return ServerResponse.ok()
                .body(this.service.getByStatus(status), EmployerRequestDTO::class.java).toMono()

    }

}