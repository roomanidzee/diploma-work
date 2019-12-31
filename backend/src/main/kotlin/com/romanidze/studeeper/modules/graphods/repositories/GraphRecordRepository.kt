package com.romanidze.studeeper.modules.graphods.repositories

import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * 01.12.2019
 *
 * Repository for graph records of system
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Repository
interface GraphRecordRepository: Neo4jRepository<GraphRecord, String> {

    fun findBySpecialization(specialization: String): List<GraphRecord>
    fun findBySpecializationAndGraduationBefore(specialization: String, graduation: LocalDateTime): List<GraphRecord>

}