package com.romanidze.studeeper.modules.worker.services.interfaces

import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import reactor.core.publisher.Flux

/**
 * 02.02.2020
 *
 * Information about worker
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
interface WorkerInfoService {
    fun getWorkerGroupmates(profileID: String): Flux<ProfileDTO>
}