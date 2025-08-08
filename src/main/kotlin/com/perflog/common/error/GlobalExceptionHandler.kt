package com.perflog.common.error

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    data class ErrorResponse(
        val status: Int,
        val message: String
    )

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        return ResponseEntity
            .status(errorCode.status)
            .body(ErrorResponse(errorCode.status, errorCode.message))
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(e: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        val code = ErrorCode.ENTITY_NOT_FOUND
        return ResponseEntity
            .status(code.status)
            .body(ErrorResponse(code.status, e.message ?: code.message))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val code = ErrorCode.INVALID_ARGUMENT
        return ResponseEntity
            .status(code.status)
            .body(ErrorResponse(code.status, e.message ?: code.message))
    }

    @ExceptionHandler(Exception::class)
    fun handleOther(e: Exception): ResponseEntity<ErrorResponse> {
        val code = ErrorCode.INVALID_ARGUMENT
        return ResponseEntity
            .status(code.status)
            .body(ErrorResponse(code.status, "예기치 못한 오류가 발생했습니다."))
    }
}