package com.romanidze.studeeper.modules.files.mappers

import com.romanidze.studeeper.config.mapstruct.MapStructConfig
import com.romanidze.studeeper.modules.files.domain.FileInfo
import com.romanidze.studeeper.modules.files.dto.FileInfoDTO
import org.mapstruct.Mapper

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Mapper(config = MapStructConfig::class)
interface FileInfoMapper {

    fun domainToDTO(file: FileInfo): FileInfoDTO
    fun dtoToDomain(file: FileInfoDTO): FileInfo

}