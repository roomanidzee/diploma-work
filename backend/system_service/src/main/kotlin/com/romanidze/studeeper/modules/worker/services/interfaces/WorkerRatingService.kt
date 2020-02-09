package com.romanidze.studeeper.modules.worker.services.interfaces

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.worker.dto.WorkerRatingDTO
import com.romanidze.studeeper.modules.worker.dto.WorkerRatingAggregatedDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 09.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface WorkerRatingService{

    fun addRating(inputRating: WorkerRatingDTO): Mono<MessageResponseDTO>
    fun getAllRatings(): Flux<WorkerRatingDTO>
    fun getAggregatedRatings(): Flux<WorkerRatingAggregatedDTO>
    fun getRatingForWorker(profileID: String): Mono<WorkerRatingAggregatedDTO>

}