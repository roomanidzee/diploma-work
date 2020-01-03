package com.romanidze.studeeper.modules.employer.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.employer.domain.EmployerVacancy
import com.romanidze.studeeper.modules.employer.dto.EmployerVacancyDTO
import org.mapstruct.Mapper

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Mapper(config = MapStructConfig::class)
interface EmployerVacancyMapper {

    fun domainToDTO(vacancy: EmployerVacancy): EmployerVacancyDTO
    fun dtoToDomain(vacancy: EmployerVacancyDTO): EmployerVacancy

}