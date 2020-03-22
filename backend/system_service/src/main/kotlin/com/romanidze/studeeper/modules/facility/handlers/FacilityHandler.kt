package com.romanidze.studeeper.modules.facility.handlers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import com.romanidze.studeeper.modules.facility.services.interfaces.FacilityService
import com.romanidze.studeeper.modules.graphods.dto.FacilityRecordDTO
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO

import reactor.core.publisher.Mono

/**
 *
 * Handler for requests with worker information
 *
 * 09.01.2019
 * @author Andrey Romanov
 */
@Component
class FacilityHandler(private val service: FacilityService){

    fun makeFacility(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(FacilityRecordDTO::class.java)
                  .flatMap{
                      ServerResponse.ok()
                                    .body(this.service.makeFacility(it), FacilityRecordDTO::class.java)
                  } 

    }

}