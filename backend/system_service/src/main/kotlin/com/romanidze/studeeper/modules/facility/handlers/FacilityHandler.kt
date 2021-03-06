package com.romanidze.studeeper.modules.facility.handlers

import com.romanidze.studeeper.modules.facility.dto.AnalysisResult
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import com.romanidze.studeeper.modules.facility.services.interfaces.FacilityService
import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext

import reactor.core.publisher.Mono

/**
 *
 * Handler for requests with worker information
 *
 * 09.01.2019
 * @author Andrey Romanov
 */
@Component
class FacilityHandler(
    private val service: FacilityService,
    private val graphService: GraphRecordService
){

    fun makeFacility(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(FacilityRecordDTO::class.java)
                  .flatMap{
                      ServerResponse.ok()
                                    .body(this.service.makeFacility(it), FacilityRecordDTO::class.java)
                  } 

    }

    fun showStudents(req: ServerRequest): Mono<ServerResponse>{

        val specialities =
                req.queryParam("specialities")
                   .get()
                   .split(",")
                   .toSet()

        return ServerResponse.ok()
                             .body(this.graphService.getBySpecialities(specialities), GraphRecordDTO::class.java)

    }

    fun launchAnalysis(req: ServerRequest): Mono<ServerResponse> {

        val fileID = req.pathVariable("file_id")

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap {
                    ServerResponse.ok()
                            .body(this.service.analyseData(it, fileID), AnalysisResult::class.java)
                }

    }

}