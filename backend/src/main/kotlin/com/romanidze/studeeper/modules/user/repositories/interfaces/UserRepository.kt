package com.romanidze.studeeper.modules.user.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.user.domain.User

import reactor.core.publisher.Mono

/**
 * Interface for UserRepository
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
interface UserRepository: CRUDRepository<User, String>{
    fun findByUsername(username: String): Mono<User>
}