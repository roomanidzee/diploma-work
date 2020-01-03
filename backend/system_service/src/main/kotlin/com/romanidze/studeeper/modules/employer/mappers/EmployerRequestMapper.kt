package com.romanidze.studeeper.modules.employer.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.employer.domain.EmployerRequest
import com.romanidze.studeeper.modules.employer.dto.EmployerRequestDTO
import org.mapstruct.Mapper

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Mapper(config = MapStructConfig::class)
interface EmployerRequestMapper {

    fun domainToDTO(request: EmployerRequest): EmployerRequestDTO
    fun dtoToDomain(request: EmployerRequestDTO): EmployerRequest

}