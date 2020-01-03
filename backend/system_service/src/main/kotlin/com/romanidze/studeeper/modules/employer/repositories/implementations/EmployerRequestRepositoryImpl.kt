package com.romanidze.studeeper.modules.employer.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.romanidze.studeeper.modules.employer.domain.EmployerRequest
import com.romanidze.studeeper.modules.employer.repositories.interfaces.EmployerRequestRepository
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Repository
class EmployerRequestRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): EmployerRequestRepository {

    override fun findByEmployerID(employerID: String): Flux<EmployerRequest> {

        val searchQuery = Query.query(
            Criteria.where("employer_id").`is`(employerID)
        )

        return this.mongoTemplate.find(searchQuery, EmployerRequest::class.java)

    }

    override fun findByWorkerID(workerID: String): Flux<EmployerRequest> {

        val searchQuery = Query.query(
            Criteria.where("worker_id").`is`(workerID)
        )

        return this.mongoTemplate.find(searchQuery, EmployerRequest::class.java)

    }

    override fun findByRequestStatus(status: String): Flux<EmployerRequest> {

        val searchQuery = Query.query(
            Criteria.where("request_status").`is`(status)
        )

        return this.mongoTemplate.find(searchQuery, EmployerRequest::class.java)

    }

    override fun findByID(id: String): Mono<EmployerRequest> {
        return this.mongoTemplate.findById(id, EmployerRequest::class.java)
    }

    override fun findAll(): Flux<EmployerRequest> {
        return this.mongoTemplate.findAll(EmployerRequest::class.java)
    }

    override fun save(item: EmployerRequest): Mono<EmployerRequest> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: EmployerRequest): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: EmployerRequest): Mono<UpdateResult> {

        val updateQuery = Query.query(
             Criteria.where("_id").`is`(item.id)
        )

        val updateFunc = Update()

        updateFunc.set("vacancy_id", item.vacancyID)
        updateFunc.set("employer_id", item.employerID)
        updateFunc.set("worker_id", item.workerID)
        updateFunc.set("request_status", item.requestStatus)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, EmployerRequest::class.java)

    }
}