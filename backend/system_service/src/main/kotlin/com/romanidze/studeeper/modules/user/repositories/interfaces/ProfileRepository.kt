package com.romanidze.studeeper.modules.user.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.user.domain.Profile
import reactor.core.publisher.Mono
import reactor.core.publisher.Flux

/**
 *
 * Interface for Profile repository
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
interface ProfileRepository: CRUDRepository<Profile, String>{
    fun findByUser(userID: String): Mono<Profile>
    fun findByIDs(profileIDs: Set<String>): Flux<Profile>
}