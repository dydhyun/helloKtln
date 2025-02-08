package com.example.helloKtln.core.exception

import com.example.helloKtln.core.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// responseEntity 로 값을 던지기위해 추가 한 어노테이션
@RestControllerAdvice
class CustomExceptionHandler {

    // 어떤 Exception 이 발생했을 때 함수를 실행할지 지정
    @ExceptionHandler(InvalidInputException::class)
    protected fun invalidInputException(ex: InvalidInputException): ResponseEntity<ErrorResponse> {
        val errors = ErrorResponse(ex.message ?: "Not Exception message")
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}