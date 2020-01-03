package com.romanidze.studeeper.modules.graphods.services.interfaces

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import reactor.core.publisher.Flux

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

}