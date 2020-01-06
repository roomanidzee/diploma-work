package com.romanidze.studeeper.modules.files.repositories.interfaces

import com.romanidze.studeeper.modules.common.CRUDRepository
import com.romanidze.studeeper.modules.files.domain.FileInfo
import reactor.core.publisher.Flux

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface FileInfoRepository: CRUDRepository<FileInfo, String> {
    fun findByType(type: String): Flux<FileInfo>
    fun findBySize(start: Int, end: Int): Flux<FileInfo>
}