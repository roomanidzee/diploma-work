package com.romanidze.houseblock.modules.user.services.implementations

import com.romanidze.houseblock.modules.user.dto.ProfileDTO
import com.romanidze.houseblock.modules.user.mappers.mapstruct.ProfileMapper
import com.romanidze.houseblock.modules.user.mappers.mybatis.ProfileDBMapper
import com.romanidze.houseblock.modules.user.services.interfaces.ProfileService

import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
        private val profileMapper: ProfileMapper,
        private val profileDBMapper: ProfileDBMapper
): ProfileService {
    override fun createProfile(profileDTO: ProfileDTO): ProfileDTO {
        val profile = this.profileMapper.dtoToDomain(profileDTO)

        this.profileDBMapper.save(profile)

        return profileDTO

    }
}