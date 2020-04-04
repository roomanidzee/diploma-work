package com.romanidze.studeeper.modules.graphods.services.interfaces

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface GraphRecordService {

    fun getByProfile(profileID: String): Flux<GraphRecordDTO>
    fun getByGroupmates(groupmates: Set<String>): Flux<GraphRecordDTO>
    fun getByFacilities(facilities: Set<String>): Flux<GraphRecordDTO>
    fun getAllRecords(): Flux<GraphRecordDTO>
    fun getBySpecialities(specialities: Set<String>): Flux<GraphRecordDTO>
    fun createGraphRecord(graphod: GraphRecordDTO): Mono<GraphRecordDTO>
    fun createMultipleRecords(graphods: Flux<GraphRecordDTO>): Flux<GraphRecordDTO>
}