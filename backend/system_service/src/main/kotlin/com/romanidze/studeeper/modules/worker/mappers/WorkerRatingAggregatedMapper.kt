package com.romanidze.studeeper.modules.worker.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.worker.domain.WorkerRatingAggregated
import com.romanidze.studeeper.modules.worker.dto.WorkerRatingAggregatedDTO
import org.mapstruct.Mapper

/**
 *
 * Mapper for WorkerRatingggregated and WorkerRatingggregatedDTO
 *
 * 02.02.2020
 * @author Andrey Romanov
 */
@Mapper(config = MapStructConfig::class)
interface WorkerRatingAggregatedMapper{

    fun domainToDTO(rating: WorkerRatingAggregated): WorkerRatingAggregatedDTO
    fun dtoToDomain(rating: WorkerRatingAggregatedDTO): WorkerRatingAggregated

}