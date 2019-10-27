package com.romanidze.houseblock.modules.user.mappers.mapstruct

import com.romanidze.houseblock.config.mapstruct.MapStructConfig
import com.romanidze.houseblock.modules.user.domain.Profile
import com.romanidze.houseblock.modules.user.dto.ProfileDTO

import org.mapstruct.Mapper

@Mapper(config= MapStructConfig::class)
interface ProfileMapper {

    fun domainToDTO(profile: Profile): ProfileDTO
    fun dtoToDomain(profileDTO: ProfileDTO): Profile

}