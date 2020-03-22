package com.romanidze.studeeper.modules.facility.services.implementations

import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO

import com.romanidze.studeeper.modules.facility.services.interfaces.FacilityService
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import com.romanidze.studeeper.modules.graphods.services.interfaces.FacilityRecordService

import org.springframework.stereotype.Service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 01.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class FacilityServiceImpl(
    private val facilityRecordService: FacilityRecordService
): FacilityService {

    override fun makeFacility(facility: FacilityRecordDTO): Mono<FacilityRecordDTO>{
        return this.facilityRecordService.createFacility(facility)
    }

}
