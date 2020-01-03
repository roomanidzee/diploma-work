package com.romanidze.studeeper.modules.employer.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.employer.domain.EmployerVacancy
import reactor.core.publisher.Flux

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface EmployerVacancyRepository: CRUDRepository<EmployerVacancy, String> {
    fun findByEmployerID(employerID: String): Flux<EmployerVacancy>
}