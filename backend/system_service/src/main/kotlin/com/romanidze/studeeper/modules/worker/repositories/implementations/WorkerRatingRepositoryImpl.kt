package com.romanidze.studeeper.modules.worker.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult

import com.romanidze.studeeper.modules.worker.domain.WorkerRating
import com.romanidze.studeeper.modules.worker.repositories.interfaces.WorkerRatingRepository

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class WorkerRatingRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): WorkerRatingRepository{

    override fun findByID(id: String): Mono<WorkerRating> {
        return this.mongoTemplate.findById(id, WorkerRating::class.java)
    }

    override fun findAll(): Flux<WorkerRating> {
        return this.mongoTemplate.findAll(WorkerRating::class.java)
    }

    override fun save(item: WorkerRating): Mono<WorkerRating> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: WorkerRating): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: WorkerRating): Mono<UpdateResult> {

        val updateQuery = Query.query(Criteria.where("_id").`is`(item.id))

        val updateFunc = Update()
        updateFunc.set("profile_id", item.profileID)
        updateFunc.set("value", item.value)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, WorkerRating::class.java)

    }

    override fun findByProfileID(profileID: String): Flux<WorkerRating> {

        val searchQuery = Query.query(
            Criteria.where("profile_id").`is`(profileID)
        )

        return this.mongoTemplate.find(searchQuery, WorkerRating::class.java)

    }

}
