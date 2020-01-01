package com.romanidze.studeeper.modules.graphods.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.graphods.domain.FacilityRecord
import reactor.core.publisher.Flux

import java.time.LocalDateTime

/**
 * 01.01.2020
 *
 * FacilityRecord Repository
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface FacilityRecordRepository: CRUDRepository<FacilityRecord, String>{
    fun findByTitle(title: String): Flux<FacilityRecord>
    fun findBySpeciality(speciality: String): Flux<FacilityRecord>
    fun findBySpecialityAndGraduation(speciality: String, graduation: LocalDateTime): Flux<FacilityRecord>
}
