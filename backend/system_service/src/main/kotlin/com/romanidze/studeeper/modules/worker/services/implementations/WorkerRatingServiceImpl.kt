package com.romanidze.studeeper.modules.worker.services.implementations

import com.romanidze.studeeper.modules.worker.dto.WorkerRatingDTO
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.worker.mappers.WorkerRatingMapper
import com.romanidze.studeeper.modules.worker.repositories.interfaces.WorkerRatingRepository
import com.romanidze.studeeper.modules.worker.services.interfaces.WorkerRatingService
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
class WorkerRatingServiceImpl(
    private val repo: WorkerRatingRepository,
    private val mapper: WorkerRatingMapper
): WorkerRatingService{

    override fun addRating(inputRating: WorkerRatingDTO): Mono<MessageResponseDTO>{

        val domainModel = this.mapper.dtoToDomain(inputRating)

        return this.repo.save(domainModel)
                        .map {
                            MessageResponseDTO("Rating saved")
                        }

    }

    override fun getAllRatings(): Flux<WorkerRatingDTO>{

        return this.repo.findAll()
                        .map{
                            this.mapper.domainToDTO(it)
                        }

    }

}
