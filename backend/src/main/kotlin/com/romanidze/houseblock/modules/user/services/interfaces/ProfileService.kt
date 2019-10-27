package com.romanidze.houseblock.modules.user.services.interfaces

import com.romanidze.houseblock.modules.user.dto.ProfileDTO

interface ProfileService {

    fun createProfile(profileDTO: ProfileDTO): ProfileDTO

}