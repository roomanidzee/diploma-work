package com.romanidze.studeeper.modules.worker.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult

import com.romanidze.studeeper.modules.worker.domain.WorkerRating
import com.romanidze.studeeper.modules.worker.domain.WorkerRatingAggregated
import com.romanidze.studeeper.modules.worker.repositories.interfaces.WorkerRatingRepository

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.AggregationResults
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
        updateFunc.set("score_description", item.scoreDescription)
        updateFunc.set("value", item.value)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, WorkerRating::class.java)

    }

    override fun findByProfileID(profileID: String): Flux<WorkerRating> {

        val searchQuery = Query.query(
            Criteria.where("profile_id").`is`(profileID)
        )

        return this.mongoTemplate.find(searchQuery, WorkerRating::class.java)

    }

    override fun aggregateRatings(): Flux<WorkerRatingAggregated>{

        val agg = Aggregation.newAggregation(
            Aggregation.group("profile_id").sum("value").`as`("value"),
            Aggregation.project("profile_id").`and`("value").previousOperation(),
            Aggregation.sort(Sort.Direction.ASC, "value")
        )

        val aggResult = this.mongoTemplate.aggregate(
            agg,
            WorkerRating::class.java,
            WorkerRatingAggregated::class.java
        )

        return aggResult.publish()

    }

    override fun aggregateRatingsForWorker(profileID: String): Mono<WorkerRatingAggregated>{

        val agg = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("_id").`is`(profileID)),
            Aggregation.group("profile_id").sum("value").`as`("value"),
            Aggregation.project("profile_id").and("value").previousOperation()
        )

        val aggResult = this.mongoTemplate.aggregate(
            agg,
            WorkerRating::class.java,
            WorkerRatingAggregated::class.java
        )

        return aggResult.publishNext()

    }

}
