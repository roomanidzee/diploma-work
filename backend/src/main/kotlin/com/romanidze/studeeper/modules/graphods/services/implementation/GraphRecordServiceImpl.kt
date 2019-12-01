package com.romanidze.studeeper.modules.graphods.services.implementation

import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import com.romanidze.studeeper.modules.graphods.repositories.GraphRecordRepository
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux

/**
 * 01.12.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class GraphRecordServiceImpl(val repo: GraphRecordRepository): GraphRecordService {

    override fun getRecordsBySpecialization(specialization: String): Flux<GraphRecord> {
        return repo.findBySpecialization(specialization).toFlux()
    }
}