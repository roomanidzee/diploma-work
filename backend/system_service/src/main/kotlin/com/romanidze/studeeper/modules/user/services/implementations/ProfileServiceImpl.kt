package com.romanidze.studeeper.modules.user.services.implementations

import com.romanidze.studeeper.modules.security.services.interfaces.AuthenticationService
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import com.romanidze.studeeper.modules.user.mappers.ProfileMapper
import com.romanidze.studeeper.modules.user.repositories.interfaces.ProfileRepository
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileService
import org.springframework.security.core.Authentication

import org.springframework.stereotype.Service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProfileServiceImpl(private val profileMapper: ProfileMapper,
                         private val profileRepository: ProfileRepository,
                         private val authenticationService: AuthenticationService): ProfileService {

    override fun getAllProfiles(): Flux<ProfileDTO> {
        return this.profileRepository.findAll()
                .map(this.profileMapper::domainToDTO)
    }

    override fun save(profileDTO: ProfileDTO): Mono<MessageResponseDTO> {

        val profile = this.profileMapper.dtoToDomain(profileDTO)

        return this.profileRepository.save(profile)
                .map { MessageResponseDTO(message="Profile saved.") }

    }

    override fun getProfileInfo(authentication: Authentication): Mono<ProfileDTO> {

        return this.authenticationService.getUserByAuthentication(authentication).flatMap {
            this.profileRepository.findByUser(it.id!!)
                                  .map(this.profileMapper::domainToDTO)
        }
    }

}
