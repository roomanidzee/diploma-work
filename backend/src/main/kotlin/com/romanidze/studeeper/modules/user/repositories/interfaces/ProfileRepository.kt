package com.romanidze.studeeper.modules.user.repositories.interfaces

import com.romanidze.studeeper.components.repositories.interfaces.CRUDRepository
import com.romanidze.studeeper.modules.user.domain.Profile

/**
 *
 * Interface for Profile repository
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
interface ProfileRepository: CRUDRepository<Profile, String>