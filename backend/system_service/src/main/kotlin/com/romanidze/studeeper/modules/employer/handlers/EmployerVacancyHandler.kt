package com.romanidze.studeeper.modules.employer.handlers

import com.romanidze.studeeper.modules.employer.dto.EmployerVacancyDTO
import com.romanidze.studeeper.modules.employer.services.interfaces.EmployerVacancyService
import com.romanidze.studeeper.modules.user.dto.MessageResponseDTO
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

/**
 * 03.01.2020
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Component
class EmployerVacancyHandler(private val service: EmployerVacancyService) {

    fun createVacancy(req: ServerRequest): Mono<ServerResponse>{

        return req.bodyToMono(EmployerVacancyDTO::class.java)
                  .flatMap {
                      ServerResponse.ok().body(this.service.createVacancy(it), MessageResponseDTO::class.java)
                  }

    }

    fun getByEmployer(req: ServerRequest): Mono<ServerResponse>{

        val employerID = req.pathVariable("id")

        return ServerResponse.ok()
                             .body(this.service.getByEmployer(employerID), EmployerVacancyDTO::class.java)

    }

    fun getAllVacancies(req: ServerRequest): Mono<ServerResponse>{

        return ServerResponse.ok()
                             .body(this.service.getAllVacancies(), EmployerVacancyDTO::class.java)

    }

    fun getByID(req: ServerRequest): Mono<ServerResponse>{

        val vacancyID = req.pathVariable("id")

        return ServerResponse.ok()
                             .body(this.service.getById(vacancyID), EmployerVacancyDTO::class.java)

    }

}