package com.romanidze.studeeper.modules.user.services.interfaces

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import org.springframework.security.core.Authentication

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 *
 * Interface for ProfileService
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
interface ProfileService {

    fun getAllProfiles(): Flux<ProfileDTO>
    fun save(profileDTO: ProfileDTO): Mono<MessageResponseDTO>
    fun getProfileInfo(authentication: Authentication): Mono<ProfileDTO>
    fun getByIDs(profileIDs: Set<String>): Flux<ProfileDTO>

}