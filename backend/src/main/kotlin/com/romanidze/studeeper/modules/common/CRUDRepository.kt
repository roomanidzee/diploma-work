package com.romanidze.studeeper.modules.common

import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * Base interface for all repositories
 *
 * 17.11.2019
 * @author Andrey Romanov
 *
 */
interface CRUDRepository<M, ID> {

    fun findByID(id: ID): Mono<M>
    fun findAll(): Flux<M>
    fun save(item: M): Mono<M>
    fun delete(item: M): Mono<DeleteResult>
    fun update(item: M): Mono<UpdateResult>

}