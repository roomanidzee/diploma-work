package com.romanidze.studeeper.modules.exceptions.handlers

import com.romanidze.studeeper.modules.exceptions.models.ServiceError

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

@ControllerAdvice
class RESTExceptionHandler {

    companion object{
        val logger: Logger = LogManager.getLogger(RESTExceptionHandler::class)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
        ex: Exception,
        exchange: ServerWebExchange
    ): ResponseEntity<ServiceError>{

        val errorMessage = "Unexpected error"
        val errorDebugMessage = "Error cause: ${ex.cause}"

        ex.printStackTrace()

        logger.error("$errorMessage \n $errorDebugMessage")

        val serviceError = ServiceError(HttpStatus.BAD_REQUEST)
        serviceError.message = errorMessage
        serviceError.debugMessage = errorDebugMessage

        return ResponseEntity(serviceError, serviceError.status!!)

    }

}