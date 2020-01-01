package com.romanidze.studeeper.modules.graphods.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import reactor.core.publisher.Flux

import java.time.LocalDateTime

/**
 * 01.01.2020
 *
 * GraphRecord Repository
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface GraphRecordRepository: CRUDRepository<GraphRecord, String>{
    fun findByProfileID(profileID: String): Flux<GraphRecord>
    fun findByGroupmates(groupmates: Set<String>): Flux<GraphRecord>
}
