package com.romanidze.studeeper.modules.graphods.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult

import com.romanidze.studeeper.modules.graphods.domain.FacilityRecord
import com.romanidze.studeeper.modules.graphods.repositories.interfaces.FacilityRecordRepository

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.time.LocalDateTime

@Repository
class FacilityRecordRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): FacilityRecordRepository{

    override fun findByID(id: String): Mono<FacilityRecord> {
        return this.mongoTemplate.findById(id, FacilityRecord::class.java)
    }

    override fun findAll(): Flux<FacilityRecord> {
        return this.mongoTemplate.findAll(FacilityRecord::class.java)
    }

    override fun save(item: FacilityRecord): Mono<FacilityRecord> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: FacilityRecord): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: FacilityRecord): Mono<UpdateResult> {

        val updateQuery = Query.query(Criteria.where("_id").`is`(item.id))

        val updateFunc = Update()
        updateFunc.set("title", item.title)
        updateFunc.set("graduation", item.graduation)
        updateFunc.set("speciality", item.speciality)
        updateFunc.set("facility_group", item.facilityGroup)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, FacilityRecord::class.java)

    }

    override fun findByTitle(title: String): Flux<FacilityRecord>{

        val searchQuery = Query.query(Criteria.where("title").`is`(title))

        return this.mongoTemplate.find(searchQuery, FacilityRecord::class.java) 

    }

    override fun findBySpeciality(speciality: String): Flux<FacilityRecord>{

        val searchQuery = Query.query(Criteria.where("speciality").`is`(speciality))

        return this.mongoTemplate.find(searchQuery, FacilityRecord::class.java)

    }

    override fun findBySpecialities(specialities: Set<String>): Flux<FacilityRecord> {

        val searchQuery = Query.query(
                Criteria.where("speciality").`in`(specialities)
        )

        return this.mongoTemplate.find(searchQuery, FacilityRecord::class.java)

    }

    override fun findBySpecialityAndGraduation(speciality: String, graduation: LocalDateTime): Flux<FacilityRecord>{

        val searchQuery = Query()

        searchQuery.addCriteria(Criteria.where("speciality").`is`(speciality))
        searchQuery.addCriteria(Criteria.where("graduation").`is`(graduation))

        return this.mongoTemplate.find(searchQuery, FacilityRecord::class.java)

    }

}
