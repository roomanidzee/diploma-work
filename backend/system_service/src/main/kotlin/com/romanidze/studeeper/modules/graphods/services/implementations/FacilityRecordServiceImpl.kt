package com.romanidze.studeeper.modules.graphods.services.implementations

import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import com.romanidze.studeeper.modules.graphods.mappers.FacilityRecordMapper
import com.romanidze.studeeper.modules.graphods.repositories.interfaces.FacilityRecordRepository
import com.romanidze.studeeper.modules.graphods.services.interfaces.FacilityRecordService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.LocalDateTime

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class FacilityRecordServiceImpl(
        private val repo: FacilityRecordRepository,
        private val mapper: FacilityRecordMapper
): FacilityRecordService {

    override fun getByTitle(title: String): Flux<FacilityRecordDTO> {

        return this.repo.findByTitle(title)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getBySpec(speciality: String): Flux<FacilityRecordDTO> {

        return this.repo.findBySpeciality(speciality)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getBySpecAndGrad(speciality: String, graduation: LocalDateTime): Flux<FacilityRecordDTO> {

        return this.repo.findBySpecialityAndGraduation(
                speciality, graduation
        ).map { this.mapper.domainToDTO(it) }

    }

    override fun getAllFacilities(): Flux<FacilityRecordDTO>{

       return this.repo.findAll()
                       .map { 
                          this.mapper.domainToDTO(it)
                       }

    }
}