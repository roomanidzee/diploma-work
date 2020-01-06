package com.romanidze.studeeper.modules.files.handlers

import com.romanidze.studeeper.modules.files.services.interfaces.FileInfoService
import com.romanidze.studeeper.modules.files.dto.FileInfoDTO
import com.romanidze.studeeper.modules.files.dto.FileInfoServiceDTO
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * 06.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class FileInfoHandler(private val service: FileInfoService){

    fun getFilesInfo(req: ServerRequest): Mono<ServerResponse>{

        return ServerResponse.ok()
                             .body(this.service.getFilesInfo(), FileInfoDTO::class.java).toMono()

    }

    fun listExternalFiles(req: ServerRequest): Mono<ServerResponse>{

        return ServerResponse.ok()
                             .body(this.service.listExternalFiles(), FileInfoServiceDTO::class.java).toMono()

    }

}