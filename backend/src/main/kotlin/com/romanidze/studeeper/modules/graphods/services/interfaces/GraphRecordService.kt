package com.romanidze.studeeper.modules.graphods.services.interfaces

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import reactor.core.publisher.Flux
import java.time.LocalDateTime

/**
 * 01.12.2019
 *
 * Business Logic for graph records
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface GraphRecordService {

    fun getRecordsBySpecialization(specialization: String): Flux<GraphRecordDTO>
    fun getRecordsBySpecializationAndGraduate(specialization: String, graduation: LocalDateTime): Flux<GraphRecordDTO>

}