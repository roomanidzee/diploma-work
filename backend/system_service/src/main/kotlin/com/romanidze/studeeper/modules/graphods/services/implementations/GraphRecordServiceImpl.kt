package com.romanidze.studeeper.modules.graphods.services.implementations

import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.mappers.GraphRecordMapper
import com.romanidze.studeeper.modules.graphods.repositories.interfaces.GraphRecordRepository
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class GraphRecordServiceImpl(
        private val repo: GraphRecordRepository,
        private val mapper: GraphRecordMapper
): GraphRecordService {

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

    override fun getBySpecialities(specialities: Set<String>): Flux<GraphRecordDTO> {

        return this.repo.findBySpecialities(specialities)
                .map {
                    this.mapper.domainToDTO(it)
                }

    }

    override fun createGraphRecord(graphod: GraphRecordDTO): Mono<GraphRecordDTO> {

        val domainEntity = this.mapper.dtoToDomain(graphod)

        return this.repo.save(domainEntity)
                        .map {
                            this.mapper.domainToDTO(it)
                        }  

    }

    override fun createMultipleRecords(graphods: Flux<GraphRecordDTO>): Flux<GraphRecordDTO>{

        val domainEntities = graphods.map {
            this.mapper.dtoToDomain(it)
        }

        return this.repo.insertMany(domainEntities)
                        .map{
                            this.mapper.domainToDTO(it)
                        }

    }


}