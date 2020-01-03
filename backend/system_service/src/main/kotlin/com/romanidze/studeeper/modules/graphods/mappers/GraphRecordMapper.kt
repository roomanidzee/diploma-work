package com.romanidze.studeeper.modules.graphods.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import org.mapstruct.Mapper

/**
 *
 * Mapper for GraphRecord and GraphRecordDTO
 *
 * 01.01.2020
 * @author Andrey Romanov
 */
@Mapper(config = MapStructConfig::class)
interface GraphRecordMapper{

    fun domainToDTO(record: GraphRecord): GraphRecordDTO
    fun dtoToDomain(record: GraphRecordDTO): GraphRecord

}
