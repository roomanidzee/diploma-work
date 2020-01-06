package com.romanidze.studeeper.modules.files.services.implementations

import com.romanidze.studeeper.modules.files.services.interfaces.FileInfoService
import com.romanidze.studeeper.modules.files.dto.FileInfoDTO
import com.romanidze.studeeper.modules.files.dto.FileInfoServiceDTO
import reactor.core.publisher.Flux
import org.springframework.stereotype.Service

import com.romanidze.studeeper.modules.files.repositories.interfaces.FileInfoRepository
import org.springframework.web.reactive.function.client.WebClient
import com.romanidze.studeeper.modules.files.mappers.FileInfoMapper

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class FileInfoServiceImpl(
   private val repo: FileInfoRepository,
   private val mapper: FileInfoMapper,
   private val fileServiceClient: WebClient
): FileInfoService {

    override fun getFilesInfo(): Flux<FileInfoDTO>{

        return this.repo.findAll()
                        .map {
                            this.mapper.domainToDTO(it)
                        }

    }

    override fun listExternalFiles(): Flux<FileInfoServiceDTO> {

        return this.fileServiceClient.get()
                                     .uri("/api/files/list")
                                     .retrieve()
                                     .bodyToFlux(FileInfoServiceDTO::class.java) 

    }

}
