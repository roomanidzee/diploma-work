package com.romanidze.studeeper.modules.graphods.services.interfaces

import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import reactor.core.publisher.Flux

/**
 * 01.12.2019
 *
 * Business Logic for graph records
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface GraphRecordService {

    fun getRecordsBySpecialization(specialization: String): Flux<GraphRecord>

}