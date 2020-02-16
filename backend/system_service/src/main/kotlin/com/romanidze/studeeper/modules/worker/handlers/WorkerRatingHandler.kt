package com.romanidze.studeeper.modules.worker.handlers

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.worker.dto.WorkerRatingDTO
import com.romanidze.studeeper.modules.worker.dto.WorkerRatingAggregatedDTO
import com.romanidze.studeeper.modules.worker.services.interfaces.WorkerRatingService

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

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
                             .body(this.service.getAllRatings(), WorkerRatingDTO::class.java)

    }

    fun aggregatedRatings(req: ServerRequest): Mono<ServerResponse>{

        return ServerResponse.ok()
                            .body(this.service.getAggregatedRatings(), WorkerRatingAggregatedDTO::class.java)

    }

    fun aggregateRatingsForWorker(req: ServerRequest): Mono<ServerResponse>{

        val workerID = req.pathVariable("worker_id")

        return ServerResponse.ok()
                            .body(
                              this.service.getRatingForWorker(workerID),
                              WorkerRatingAggregatedDTO::class.java
                            )

    }

}
