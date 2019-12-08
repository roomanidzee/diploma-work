package com.romanidze.studeeper.modules.graphods.repositories

import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * 01.12.2019
 *
 * Repository for graph records of system
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Repository
interface GraphRecordRepository: ReactiveNeo4jRepository<GraphRecord, String> {

    fun findBySpecialization(specialization: String): Flux<GraphRecord>

}