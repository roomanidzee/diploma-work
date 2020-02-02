package com.romanidze.studeeper.modules.worker.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.worker.domain.WorkerRating
import com.romanidze.studeeper.modules.worker.domain.WorkerRatingAggregated
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 01.01.2020
 *
 * WorkerRating Repository
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface WorkerRatingRepository: CRUDRepository<WorkerRating, String>{
    fun findByProfileID(profileID: String): Flux<WorkerRating>
    fun aggregateRatings(): Flux<WorkerRatingAggregated>
    fun aggregateRatingsForWorker(profileID: String): Mono<WorkerRatingAggregated>
} 
