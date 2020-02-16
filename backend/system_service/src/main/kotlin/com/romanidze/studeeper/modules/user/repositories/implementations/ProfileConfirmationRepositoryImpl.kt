package com.romanidze.studeeper.modules.user.repositories.implementations

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.romanidze.studeeper.modules.user.domain.ProfileConfirmation
import com.romanidze.studeeper.modules.user.repositories.interfaces.ProfileConfirmationRepository
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 16.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Repository
class ProfileConfirmationRepositoryImpl(
        private val mongoTemplate: ReactiveMongoTemplate
): ProfileConfirmationRepository {

    override fun findByConfirmString(confirmString: String): Mono<ProfileConfirmation> {

        val searchQuery = Query.query(
            Criteria.where("confirmation_string").`is`(confirmString)
        )

        return this.mongoTemplate.findOne(searchQuery, ProfileConfirmation::class.java)

    }

    override fun findByID(id: String): Mono<ProfileConfirmation> {
        return this.mongoTemplate.findById(id, ProfileConfirmation::class.java)
    }

    override fun findAll(): Flux<ProfileConfirmation> {
        return this.mongoTemplate.findAll(ProfileConfirmation::class.java)
    }

    override fun save(item: ProfileConfirmation): Mono<ProfileConfirmation> {
        return this.mongoTemplate.save(item)
    }

    override fun delete(item: ProfileConfirmation): Mono<DeleteResult> {
        return this.mongoTemplate.remove(item)
    }

    override fun update(item: ProfileConfirmation): Mono<UpdateResult> {

        val updateQuery = Query.query(
           Criteria.where("_id").`is`(item.id)
        )

        val updateFunc = Update()

        updateFunc.set("profile_id", item.profileID)
        updateFunc.set("confirmation_string", item.confirmString)

        return this.mongoTemplate.updateFirst(updateQuery, updateFunc, ProfileConfirmation::class.java)

    }
}