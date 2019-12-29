package com.romanidze.studeeper.modules.user.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult

import com.romanidze.studeeper.modules.user.domain.Profile
import com.romanidze.studeeper.modules.user.repositories.interfaces.ProfileRepository

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class ProfileRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): ProfileRepository {

    override fun findByID(id: String): Mono<Profile> {
        return this.mongoTemplate.findById(id, Profile::class.java)
    }

    override fun findAll(): Flux<Profile> {
        return this.mongoTemplate.findAll(Profile::class.java)
    }

    override fun save(item: Profile): Mono<Profile> {
       return this.mongoTemplate.save(item)
    }

    override fun delete(item: Profile): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: Profile): Mono<UpdateResult> {

        val updateQuery = Query.query(Criteria.where("_id").`is`(item.id))

        val updateFunc = Update()
        updateFunc.set("surname", item.surname)
        updateFunc.set("name", item.name)
        updateFunc.set("patronymic", item.patronymic)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, Profile::class.java)

    }

    override fun findByUser(userID: String): Mono<Profile> {

        val searchQuery = Query.query(Criteria.where("user_id").`is`(userID))

        return this.mongoTemplate.findOne(searchQuery, Profile::class.java)

    }

}