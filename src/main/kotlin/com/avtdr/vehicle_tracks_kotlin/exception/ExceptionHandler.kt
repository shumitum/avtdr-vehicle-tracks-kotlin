package com.avtdr.vehicle_tracks_kotlin.exception

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    data class ErrorResponse(val message: String?, val status: String, val path: String)

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler
    fun handleTimeValidationException(
        exc: TimeValidationException, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        log.warn("Получен статус 400 BAD_REQUEST: ${exc.message}")
        return ResponseEntity(
            ErrorResponse(exc.message, HttpStatus.BAD_REQUEST.toString(), request.requestURI),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler
    fun handleNoSuchElementException(
        exc: NoSuchElementException, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        log.warn("Получен статус 404 NOT FOUND: ${exc.message}")
        return ResponseEntity(
            ErrorResponse(exc.message, HttpStatus.NOT_FOUND.toString(), request.requestURI),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler
    fun handleConstraintViolationException(
        exc: ConstraintViolationException, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        log.warn("Получен статус 400 BAD_REQUEST: ${exc.message}")
        return ResponseEntity(
            ErrorResponse(exc.message, HttpStatus.BAD_REQUEST.toString(), request.requestURI),
            HttpStatus.BAD_REQUEST
        )
    }
}