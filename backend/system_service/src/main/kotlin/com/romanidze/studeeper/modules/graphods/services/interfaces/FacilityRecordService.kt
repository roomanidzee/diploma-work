package com.romanidze.studeeper.modules.graphods.services.interfaces

import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import reactor.core.publisher.Flux
import java.time.LocalDateTime

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface FacilityRecordService {
    fun getByTitle(title: String): Flux<FacilityRecordDTO>
    fun getBySpec(speciality: String): Flux<FacilityRecordDTO>
    fun getBySpecAndGrad(speciality: String, graduation: LocalDateTime): Flux<FacilityRecordDTO>
    fun getAllFacilities(): Flux<FacilityRecordDTO>
}