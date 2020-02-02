package com.romanidze.studeeper.modules.worker.handlers

import com.romanidze.studeeper.modules.worker.services.interfaces.WorkerInfoService
import com.romanidze.studeeper.modules.user.dto.ProfileDTO

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 *
 * Handler for requests with worker information
 *
 * 09.01.2019
 * @author Andrey Romanov
 */
@Component
class WorkerInfoHandler(private val service: WorkerInfoService) {

    fun getWorkerGroupmates(req: ServerRequest): Mono<ServerResponse>{

        val workerID = req.pathVariable("worker_id")

        return ServerResponse.ok()
                             .body(
                                this.service.getWorkerGroupmates(workerID), ProfileDTO::class.java
                            ).toMono()

    }

}