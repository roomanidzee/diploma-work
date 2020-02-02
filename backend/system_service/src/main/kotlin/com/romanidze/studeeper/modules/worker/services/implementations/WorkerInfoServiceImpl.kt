package com.romanidze.studeeper.modules.worker.services.implementations

import com.romanidze.studeeper.modules.user.dto.ProfileDTO
import com.romanidze.studeeper.modules.graphods.dto.GraphRecordDTO
import com.romanidze.studeeper.modules.graphods.services.interfaces.GraphRecordService
import com.romanidze.studeeper.modules.user.services.interfaces.ProfileService
import com.romanidze.studeeper.modules.worker.services.interfaces.WorkerInfoService

import org.springframework.stereotype.Service

import reactor.core.publisher.Flux
import java.util.stream.Collectors

/**
 * 02.02.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
class WorkerInfoServiceImpl(
    private val graphService: GraphRecordService,
    private val profileService: ProfileService
): WorkerInfoService {

    override fun getWorkerGroupmates(profileID: String): Flux<ProfileDTO> {

        val graphObjects: Flux<GraphRecordDTO> = this.graphService.getByProfile(profileID)

        val profileIDs: Set<String> = graphObjects.toStream()
                                                  .flatMap {
                                                      it.groupmates.stream()
                                                  }
                                                  .collect(Collectors.toSet())

        return this.profileService.getByIDs(profileIDs)

    }

}
