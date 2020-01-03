package com.romanidze.studeeper.modules.employer.services.interfaces

import com.romanidze.studeeper.modules.employer.dto.EmployerVacancyDTO
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface EmployerVacancyService {

    fun createVacancy(vacancy: EmployerVacancyDTO): Mono<MessageResponseDTO>
    fun getByEmployer(employerID: String): Flux<EmployerVacancyDTO>
    fun getAllVacancies(): Flux<EmployerVacancyDTO>
    fun getById(vacancyID: String): Mono<EmployerVacancyDTO>

}