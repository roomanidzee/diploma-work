package com.romanidze.studeeper.modules.user.handlers

import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileService
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

/**
 *
 * Handler for requests with Profile
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@Component
class ProfileHandler(private val profileService: ProfileService) {

    /**
     *
     * Retrieve all profiles from system
     *
     * @param req: input request
     * @return all profiles
     */
    fun all(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
                .body(this.profileService.getAllProfiles(), ProfileDTO::class.java)
    }

    /**
     *
     * Create new profile
     *
     * @param req: input request
     * @return result of creation
     */
    fun create(req: ServerRequest): Mono<ServerResponse> {
        return req.bodyToMono(ProfileDTO::class.java)
                .flatMap {
                    ServerResponse.ok()
                            .body(this.profileService.save(it), MessageResponseDTO::class.java)
                }
    }

    fun getProfileInfo(req: ServerRequest): Mono<ServerResponse>{

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap {

                    ServerResponse.ok()
                            .body(this.profileService.getProfileInfo(it), ProfileDTO::class.java)

                }

    }

}