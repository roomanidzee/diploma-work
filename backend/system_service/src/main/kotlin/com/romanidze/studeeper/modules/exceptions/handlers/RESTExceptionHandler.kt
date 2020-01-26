package com.romanidze.studeeper.modules.exceptions.handlers

import com.romanidze.studeeper.modules.exceptions.models.ServiceError

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import org.springframework.beans.ConversionNotSupportedException
import org.springframework.beans.TypeMismatchException

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException

import org.springframework.validation.BindException

import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

import javax.validation.ConstraintViolationException

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * 26.01.2020
 *
 * Exception handling component
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@RestControllerAdvice
class RESTExceptionHandler {

    companion object{
        val logger: Logger = LogManager.getLogger(RESTExceptionHandler::class)
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    fun handleUnsupportedMediaType(
        ex: HttpMediaTypeNotSupportedException
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable {

            val sb = StringBuilder()

            sb.append("Unsupported media type: ${ex.contentType}. ")
              .append("Supported: ")

            ex.supportedMediaTypes
              .forEach {
                  sb.append(it).append(", ")
              }

            val errorMessage = sb.substring(0, sb.length - 2)
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, errorMessage, ex)

            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    fun handleMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable {

            val sb = StringBuilder()

            sb.append("Unsupported request type: ${ex.method}. ")
              .append("Supported: ")

            ex.supportedHttpMethods!!
              .forEach{
                  sb.append(it).append(", ")
              }

            val errorMessage = sb.substring(0, sb.length - 2)
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.METHOD_NOT_ALLOWED, errorMessage, ex)

            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleArgumentNotValid(
        ex: MethodArgumentNotValidException
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable {

            val errorMessage = "Model validation error"
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.BAD_REQUEST)
            serviceError.message = errorMessage
            serviceError.addValidationFieldErrors(ex.bindingResult.fieldErrors)
            serviceError.addValidationObjectErrors(ex.bindingResult.globalErrors)

            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBindException(
        ex: BindException
    ): Mono<ResponseEntity<Any>>{

       return Mono.fromCallable {

            val errorMessage = "Wrong entity / endpoint request"
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.BAD_REQUEST)
            serviceError.message = errorMessage
            serviceError.addValidationFieldErrors(ex.bindingResult.fieldErrors)
            serviceError.addValidationObjectErrors(ex.bindingResult.globalErrors)

            ResponseEntity(serviceError, serviceError.status!!)

       }

    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMessageNotReadable(
        ex: HttpMessageNotReadableException
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable {

            val errorMessage = "JSON body is not readable"
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.BAD_REQUEST, errorMessage, ex)
            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

    @ExceptionHandler(HttpMessageNotWritableException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleMessageNotWritable(
        ex: HttpMessageNotWritableException
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable {

            val errorMessage = "JSON body is not writable"
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, ex)
            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handlerMethodArgumentTypeMismatch(
        ex: MethodArgumentTypeMismatchException
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable{

            val errorMessage = "Parameter ${ex.name} with type ${ex.value} " +
                               "cannot be converted to type ${ex.requiredType!!.simpleName}"
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.BAD_REQUEST)
            serviceError.message = errorMessage
            serviceError.debugMessage = ex.message

            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

    @ExceptionHandler(TypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTypeMismatch(
        ex: TypeMismatchException
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable {

            val errorMessage = "Value ${ex.value} for ${ex.propertyName} must be ${ex.requiredType} type"
            logger.error(errorMessage)
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.BAD_REQUEST)
            serviceError.message = errorMessage
            serviceError.debugMessage = ex.message

            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handlerUnclassifiedException(
        ex: Exception
    ): Mono<ResponseEntity<Any>>{

        return Mono.fromCallable {

            val errorMessage = "Unclassifed exception".
            val errorDebugMessage = "Exception cause: ${ex.cause}"

            logger.error("$errorMessage \n $errorDebugMessage")
            logger.error(ex.printStackTrace())

            val serviceError = ServiceError(HttpStatus.INTERNAL_SERVER_ERROR)
            serviceError.message = errorMessage
            serviceError.debugMessage = errorDebugMessage

            ResponseEntity(serviceError, serviceError.status!!)

        }

    }

}