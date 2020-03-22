package com.romanidze.studeeper.modules.facility.services.interfaces

import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 01.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface FacilityService{
    fun makeFacility(facility: FacilityRecordDTO): Mono<FacilityRecordDTO>
}