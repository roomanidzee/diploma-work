package com.romanidze.studeeper.modules.user.services.implementations

import com.romanidze.studeeper.modules.security.services.interfaces.AuthenticationService
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import com.romanidze.studeeper.modules.user.mappers.ProfileMapper
import com.romanidze.studeeper.modules.user.repositories.interfaces.ProfileRepository
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileConfirmationService
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileService
import org.springframework.security.core.Authentication

import org.springframework.stereotype.Service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProfileServiceImpl(
    private val profileMapper: ProfileMapper,
    private val profileRepository: ProfileRepository,
    private val authenticationService: AuthenticationService,
    private val confirmService: ProfileConfirmationService
): ProfileService {

    override fun getAllProfiles(): Flux<ProfileDTO> {
        return this.profileRepository.findAll()
                .map(this.profileMapper::domainToDTO)
    }

    override fun save(profileDTO: ProfileDTO): Mono<MessageResponseDTO> {

        val profile = this.profileMapper.dtoToDomain(profileDTO)
        val savedProfile = this.profileRepository.save(profile)

        val sendedMail = savedProfile.flatMap {
            this.confirmService.createConfirmation(it)
        }

        return sendedMail.map { MessageResponseDTO(message="Profile saved.") }

    }

    override fun getProfileInfo(authentication: Authentication): Mono<ProfileDTO> {

        return this.authenticationService.getUserByAuthentication(authentication).flatMap {
            this.profileRepository.findByUser(it.id!!)
                                  .map(this.profileMapper::domainToDTO)
        }
    }

    override fun getByIDs(profileIDs: Set<String>): Flux<ProfileDTO> {

        return this.profileRepository.findByIDs(profileIDs)
                                     .map(this.profileMapper::domainToDTO)

    }

}
