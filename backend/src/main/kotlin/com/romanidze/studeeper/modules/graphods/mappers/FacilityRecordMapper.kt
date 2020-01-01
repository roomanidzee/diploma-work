package com.romanidze.studeeper.modules.graphods.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.graphods.domain.FacilityRecord
import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping

/**
 *
 * Mapper for FacilityRecord and FacilityRecordDTO
 *
 * 01.01.2020
 * @author Andrey Romanov
 */
@Mapper(config = MapStructConfig::class)
interface FacilityRecordMapper{

    @Mapping(source = "graduation", target = "graduation", dateFormat = "yyyy-MM-dd HH:mm:ss") 
    fun domainToDTO(facility: FacilityRecord): FacilityRecordDTO

    @Mapping(source = "graduation", target = "graduation", dateFormat = "yyyy-MM-dd HH:mm:ss")
    fun dtoToDomain(facility: FacilityRecordDTO): FacilityRecord


}