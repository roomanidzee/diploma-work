package com.romanidze.studeeper.modules.graphods.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import org.mapstruct.Mapper

/**
 * 09.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Mapper(config = MapStructConfig::class)
interface GraphRecordMapper {

    fun domainToDTO(record: GraphRecord): GraphRecordDTO
    fun dtoToDomain(record: GraphRecordDTO): GraphRecord

}