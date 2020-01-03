package com.romanidze.studeeper.modules.employer.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.romanidze.studeeper.modules.employer.domain.EmployerVacancy
import com.romanidze.studeeper.modules.employer.repositories.interfaces.EmployerVacancyRepository
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
class EmployerVacancyRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): EmployerVacancyRepository {

    override fun findByEmployerID(employerID: String): Flux<EmployerVacancy> {

        val searchQuery = Query.query(
            Criteria.where("employer_id").`is`(employerID)
        )

        return this.mongoTemplate.find(searchQuery, EmployerVacancy::class.java)

    }

    override fun findByID(id: String): Mono<EmployerVacancy> {
        return this.mongoTemplate.findById(id, EmployerVacancy::class.java)
    }

    override fun findAll(): Flux<EmployerVacancy> {
        return this.mongoTemplate.findAll(EmployerVacancy::class.java)
    }

    override fun save(item: EmployerVacancy): Mono<EmployerVacancy> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: EmployerVacancy): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: EmployerVacancy): Mono<UpdateResult> {

        val updateQuery = Query.query(
                Criteria.where("_id").`is`(item.id)
        )

        val updateFunc = Update()

        updateFunc.set("employer_id", item.employerID)
        updateFunc.set("description", item.description)
        updateFunc.set("tags", item.tags)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, EmployerVacancy::class.java)

    }
}