package com.romanidze.studeeper.modules.user.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.user.domain.Profile
import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import org.mapstruct.Mapper

/**
 *
 * Mapper for Profile and ProfileDTO
 *
 * 17.11.2019
 * @author Andrey Romanov
 */
@Mapper(config = MapStructConfig::class)
interface ProfileMapper {

    fun domainToDTO(profile: Profile): ProfileDTO
    fun dtoToDomain(profile: ProfileDTO): Profile

}