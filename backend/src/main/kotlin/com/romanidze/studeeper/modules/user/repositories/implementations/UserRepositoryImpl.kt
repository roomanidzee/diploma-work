package com.romanidze.studeeper.modules.user.repositories.implementations

import com.mongodb.client.result.DeleteResult

import com.romanidze.studeeper.modules.user.domain.User
import com.romanidze.studeeper.modules.user.repositories.interfaces.UserRepository

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class UserRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): UserRepository {

    override fun findByID(id: String): Mono<User> {
        return this.mongoTemplate.findById(id, User::class.java)
    }

    override fun findAll(): Flux<User> {
        return this.mongoTemplate.findAll(User::class.java)
    }

    override fun save(item: User): Mono<User> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: User): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: User): Mono<User> {

        val updateQuery = Query.query(Criteria.where("_id").`is`(item.id))

        val updateFunc = Update()
        updateFunc.set("username", item.username)
        updateFunc.set("password", item.password)

        return this.mongoTemplate.findAndModify(updateQuery, updateFunc, User::class.java)

    }
}