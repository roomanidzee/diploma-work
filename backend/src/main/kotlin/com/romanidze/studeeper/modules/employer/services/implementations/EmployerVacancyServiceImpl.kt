package com.romanidze.studeeper.modules.employer.services.implementations

import com.romanidze.studeeper.modules.employer.dto.EmployerVacancyDTO
import com.romanidze.studeeper.modules.employer.mappers.EmployerVacancyMapper
import com.romanidze.studeeper.modules.employer.repositories.interfaces.EmployerVacancyRepository
import com.romanidze.studeeper.modules.employer.services.interfaces.EmployerVacancyService
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
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
class EmployerVacancyServiceImpl(
        private val repo: EmployerVacancyRepository,
        private val mapper: EmployerVacancyMapper
): EmployerVacancyService {

    override fun createVacancy(vacancy: EmployerVacancyDTO): Mono<MessageResponseDTO> {

        val vacancyModel = this.mapper.dtoToDomain(vacancy)

        return this.repo.save(vacancyModel)
                .map {
                    MessageResponseDTO("Vacancy saved")
                }

    }

    override fun getByEmployer(employerID: String): Flux<EmployerVacancyDTO> {
        return this.repo.findByEmployerID(employerID)
                .map {
                    this.mapper.domainToDTO(it)
                }
    }

    override fun getAllVacancies(): Flux<EmployerVacancyDTO> {
        return this.repo.findAll()
                .map {
                    this.mapper.domainToDTO(it)
                }
    }

    override fun getById(vacancyID: String): Mono<EmployerVacancyDTO> {
        return this.repo.findByID(vacancyID)
                .map {
                    this.mapper.domainToDTO(it)
                }
    }
}