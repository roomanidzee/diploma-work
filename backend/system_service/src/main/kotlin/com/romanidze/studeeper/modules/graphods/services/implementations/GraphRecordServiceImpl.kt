package com.romanidze.studeeper.modules.graphods.services.implementations

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.mappers.GraphRecordMapper
import com.romanidze.studeeper.modules.graphods.repositories.interfaces.GraphRecordRepository
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class GraphRecordServiceImpl(
        private val repo: GraphRecordRepository,
        private val mapper: GraphRecordMapper): GraphRecordService {

    override fun getByProfile(profileID: String): Flux<GraphRecordDTO> {

        return this.repo.findByProfileID(profileID)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getByGroupmates(groupmates: Set<String>): Flux<GraphRecordDTO> {

        return this.repo.findByGroupmates(groupmates)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getByFacilities(facilities: Set<String>): Flux<GraphRecordDTO> {

        return this.repo.findByFacilities(facilities)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun getAllRecords(): Flux<GraphRecordDTO> {

        return this.repo.findAll()
                        .map {
                            this.mapper.domainToDTO(it)
                        }

    }


}