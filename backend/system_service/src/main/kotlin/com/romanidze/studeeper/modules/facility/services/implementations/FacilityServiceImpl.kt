package com.romanidze.studeeper.modules.facility.services.implementations

import com.romanidze.studeeper.modules.facility.dto.AnalysisResult
import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO

import com.romanidze.studeeper.modules.facility.services.interfaces.FacilityService
import com.romanidze.studeeper.modules.graphods.services.interfaces.FacilityRecordService
import com.romanidze.studeeper.modules.security.authentication.JWTAuthentication
import com.romanidze.studeeper.modules.security.properties.JWTProperties
import org.springframework.security.core.Authentication

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

import reactor.core.publisher.Mono

/**
 * 01.03.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class FacilityServiceImpl(
    private val facilityRecordService: FacilityRecordService,
    private val analysisServiceClient: WebClient,
    private val jwtProps: JWTProperties
): FacilityService {

    override fun makeFacility(facility: FacilityRecordDTO): Mono<FacilityRecordDTO>{
        return this.facilityRecordService.createFacility(facility)
    }

    override fun analyseData(authentication: Authentication, fileID: String): Mono<AnalysisResult> {

        val tokenAuth = authentication as JWTAuthentication
        val token = tokenAuth.name

        return analysisServiceClient.get()
                .uri("/analyse/${fileID}/predict")
                .header(jwtProps.header, token)
                .retrieve()
                .bodyToMono(AnalysisResult::class.java)
    }

}
