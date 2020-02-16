package com.romanidze.studeeper.modules.employer.handlers

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import com.romanidze.studeeper.modules.graphods.services.interfaces.FacilityRecordService
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService


import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

/**
 * 02.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class EmployerInfoHandler(
    private val graphService: GraphRecordService,
    private val facilityService: FacilityRecordService
){

    fun getAllFacilities(req: ServerRequest): Mono<ServerResponse>{

        return ServerResponse.ok()
                             .body(this.facilityService.getAllFacilities(), FacilityRecordDTO::class.java)

    }

    fun getFacilityByTitle(req: ServerRequest): Mono<ServerResponse>{

        val facilityTitle = req.queryParam("facility_title").get()

        return ServerResponse.ok()
                             .body(this.facilityService.getByTitle(facilityTitle), FacilityRecordDTO::class.java)

    }

    fun getFacilityBySpec(req: ServerRequest): Mono<ServerResponse>{

        val facilitySpec = req.queryParam("facility_spec").get()

        return ServerResponse.ok()
                             .body(this.facilityService.getBySpec(facilitySpec), FacilityRecordDTO::class.java)

    }

    fun getFacilityBySpecAndGrad(req: ServerRequest): Mono<ServerResponse>{

        val facilitySpec = req.queryParam("facility_spec").get()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss")
        val graduationTime = LocalDateTime.parse(
            req.queryParam("facility_grad").get(),
            formatter
        )

        return ServerResponse.ok()
                             .body(
                               this.facilityService.getBySpecAndGrad(facilitySpec, graduationTime),
                               FacilityRecordDTO::class.java
                             )

    }

    fun getAllGraphs(req: ServerRequest): Mono<ServerResponse> {

        return ServerResponse.ok()
                             .body(this.graphService.getAllRecords(), GraphRecordDTO::class.java)

    }

}
