package com.romanidze.studeeper.modules.graphods.services.implementation

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.mappers.GraphRecordMapper
import com.romanidze.studeeper.modules.graphods.repositories.GraphRecordRepository
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux
import java.time.LocalDateTime

/**
 * 01.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class GraphRecordServiceImpl(
        private val repo: GraphRecordRepository,
        private val mapper: GraphRecordMapper
): GraphRecordService {

    override fun getRecordsBySpecialization(specialization: String): Flux<GraphRecordDTO> {
        return repo.findBySpecialization(specialization).toFlux()
                   .map(this.mapper::domainToDTO)
    }

    override fun getRecordsBySpecializationAndGraduate(specialization: String, graduation: LocalDateTime): Flux<GraphRecordDTO> {
        return repo.findBySpecializationAndGraduationBefore(
                specialization, graduation
        ).toFlux().map(this.mapper::domainToDTO)
    }
}