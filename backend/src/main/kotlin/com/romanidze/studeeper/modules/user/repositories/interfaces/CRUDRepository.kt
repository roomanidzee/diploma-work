package com.romanidze.studeeper.modules.user.repositories.interfaces

import com.mongodb.client.result.DeleteResult

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CRUDRepository<M> {

    fun findAll(): Flux<M>
    fun save(item: M): Mono<M>
    fun delete(item: M): Mono<DeleteResult>
    fun update(item: M): Mono<M>

}