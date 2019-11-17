package com.romanidze.studeeper.modules.user.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.user.domain.User
import com.romanidze.studeeper.modules.user.dto.UserDTO

import org.mapstruct.Mapper

/**
 *
 * Mapper for User and UserDTO
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@Mapper(config = MapStructConfig::class)
interface UserMapper {

    fun domainToDTO(user: User): UserDTO
    fun dtoToDomain(userDTO: UserDTO): User

}