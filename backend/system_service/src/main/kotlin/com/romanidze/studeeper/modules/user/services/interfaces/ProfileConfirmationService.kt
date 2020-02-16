package com.romanidze.studeeper.modules.user.services.interfaces

import com.romanidze.studeeper.modules.user.domain.Profile
import com.romanidze.studeeper.modules.user.domain.ProfileConfirmation
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import reactor.core.publisher.Mono

/**
 * 16.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface ProfileConfirmationService {

    fun createConfirmation(input: Profile): Mono<ProfileConfirmation>
    fun confirmProfile(input: String): Mono<MessageResponseDTO>

}