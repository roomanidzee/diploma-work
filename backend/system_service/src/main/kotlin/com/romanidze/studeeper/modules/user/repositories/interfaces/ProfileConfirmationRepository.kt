package com.romanidze.studeeper.modules.user.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.user.domain.ProfileConfirmation
import reactor.core.publisher.Mono

/**
 * 16.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface ProfileConfirmationRepository: CRUDRepository<ProfileConfirmation, String> {
    fun findByConfirmString(confirmString: String): Mono<ProfileConfirmation>
}