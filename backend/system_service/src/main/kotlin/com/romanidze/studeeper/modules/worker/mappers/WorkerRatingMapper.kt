package com.romanidze.studeeper.modules.worker.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.worker.domain.WorkerRating
import com.romanidze.studeeper.modules.worker.dto.WorkerRatingDTO
import org.mapstruct.Mapper

/**
 *
 * Mapper for WorkerRating and WorkerRatingDTO
 *
 * 09.01.2020
 * @author Andrey Romanov
 */
@Mapper(config = MapStructConfig::class)
interface WorkerRatingMapper{

    fun domainToDTO(rating: WorkerRating): WorkerRatingDTO
    fun dtoToDomain(rating: WorkerRatingDTO): WorkerRating

}