package com.romanidze.studeeper.modules.worker.handlers

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.worker.dto.WorkerRatingDTO
import com.romanidze.studeeper.modules.worker.services.interfaces.WorkerRatingService

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 *
 * Handler for requests with Profile
 *
 * 09.01.2019
 * @author Andrey Romanov
 */
@Component
class WorkerRatingHandler(private val service: WorkerRatingService){

    fun rateWorker(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(WorkerRatingDTO::class.java)
                  .flatMap{
                      ServerResponse.ok()
                                    .body(this.service.addRating(it), MessageResponseDTO::class.java)
                  }

    }

    fun allRatings(req: ServerRequest): Mono<ServerResponse>{

        return ServerResponse.ok()
                             .body(this.service.getAllRatings(), WorkerRatingDTO::class.java).toMono()

    }

}
