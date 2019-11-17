package com.romanidze.studeeper.modules.user.repositories.interfaces

import com.romanidze.studeeper.components.repositories.interfaces.CRUDRepository
import com.romanidze.studeeper.modules.user.domain.User

/**
 * Interface for UserRepository
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
interface UserRepository: CRUDRepository<User, String>