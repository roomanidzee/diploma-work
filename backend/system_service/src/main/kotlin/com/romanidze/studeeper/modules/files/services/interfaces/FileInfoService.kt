package com.romanidze.studeeper.modules.files.services.interfaces

import com.romanidze.studeeper.modules.files.dto.FileInfoDTO
import com.romanidze.studeeper.modules.files.dto.FileInfoServiceDTO
import reactor.core.publisher.Flux

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface FileInfoService {
    fun getFilesInfo(): Flux<FileInfoDTO>
    fun listExternalFiles(): Flux<FileInfoServiceDTO>
}