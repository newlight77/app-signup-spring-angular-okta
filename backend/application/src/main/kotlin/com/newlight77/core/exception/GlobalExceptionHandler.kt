package com.newlight77.core.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.ResourceAccessException
import java.lang.RuntimeException
import java.time.Instant
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
            HttpMessageNotReadableException::class,
            MethodArgumentNotValidException::class,
            MissingServletRequestParameterException::class,
            ForbiddenException::class)
    fun handle400Exception(request: HttpServletRequest, ex: Exception): Any {
        return ExceptionDetail.Builder()
                .classname(ex.javaClass.name)
                .date(Instant.now().toString())
                .message(ex.localizedMessage)
                .path(request.requestURI)
                .params(request.queryString)
                .build()
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(
            BadCredentialsException::class,
            UnauthorizedException::class)
    @ResponseBody
    fun handle401Exception(request: HttpServletRequest, ex: java.lang.Exception): Any {
        return ExceptionDetail.Builder()
                .classname(ex.javaClass.name)
                .date(Instant.now().toString())
                .message(ex.localizedMessage)
                .path(request.requestURI)
                .params(request.queryString)
                .build()
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException::class)
    fun handle409Exception(request: HttpServletRequest, ex: java.lang.Exception): Any {
        return ExceptionDetail.Builder()
                .classname(ex.javaClass.name)
                .date(Instant.now().toString())
                .message(ex.localizedMessage)
                .path(request.requestURI)
                .params(request.queryString)
                .build()
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceAccessException::class)
    fun handle404Exception(request: HttpServletRequest, ex: java.lang.Exception): Any {
        return ExceptionDetail.Builder()
                .classname(ex.javaClass.name)
                .date(Instant.now().toString())
                .message(ex.localizedMessage)
                .path(request.requestURI)
                .params(request.queryString)
                .build()
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
            Exception::class,
            java.lang.Exception::class,
            NullPointerException::class,
            java.lang.NullPointerException::class,
            RuntimeException::class,
            java.lang.RuntimeException::class
    )
    fun handle500Exception(request: HttpServletRequest, ex: java.lang.Exception): Any {
        return ExceptionDetail.Builder()
                .classname(ex.javaClass.name)
                .date(Instant.now().toString())
                .message(ex.localizedMessage)
                .path(request.requestURI)
                .params(request.queryString)
                .build()
    }
}