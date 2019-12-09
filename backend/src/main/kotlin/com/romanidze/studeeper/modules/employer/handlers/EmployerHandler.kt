package com.romanidze.studeeper.modules.employer.handlers

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * 09.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class EmployerHandler(private val recordService: GraphRecordService) {

    fun getActualRecords(req: ServerRequest): Mono<ServerResponse>{

        val specialization = req.queryParam("specialization")
                                .get()
                                .toMono()

        return specialization.flatMap {
            ServerResponse.ok()
                    .body(
                       this.recordService.getRecordsBySpecialization(it),
                       GraphRecordDTO::class.java
                    )
        }

    }

}