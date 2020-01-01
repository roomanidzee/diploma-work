package com.romanidze.studeeper.modules.graphods.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult

import com.romanidze.studeeper.modules.graphods.domain.GraphRecord
import com.romanidze.studeeper.modules.graphods.repositories.interfaces.GraphRecordRepository

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class GraphRecordRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): GraphRecordRepository{

    override fun findByID(id: String): Mono<GraphRecord> {
        return this.mongoTemplate.findById(id, GraphRecord::class.java)
    }

    override fun findAll(): Flux<GraphRecord> {
        return this.mongoTemplate.findAll(GraphRecord::class.java)
    }

    override fun save(item: GraphRecord): Mono<GraphRecord> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: GraphRecord): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: GraphRecord): Mono<UpdateResult> {

        val updateQuery = Query.query(Criteria.where("_id").`is`(item.id))

        val updateFunc = Update()
        updateFunc.set("facility_id", item.facilityID)
        updateFunc.set("profile_id", item.profileID)
        updateFunc.set("groupmates", item.groupmates)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, GraphRecord::class.java)

    }

    override fun findByProfileID(profileID: String): Flux<GraphRecord>{

        val searchQuery = Query.query(
            Criteria.where("profile_id").`is`(profileID)
        )

        return this.mongoTemplate.find(searchQuery, GraphRecord::class.java)

    }

    override fun findByGroupmates(groupmates: Set<String>): Flux<GraphRecord>{

        val searchQuery = Query.query(
            Criteria.where("groupmates").`in`(groupmates)
        )

        return this.mongoTemplate.find(searchQuery, GraphRecord::class.java)

    }

}
