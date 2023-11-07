package br.com.symon.userbase.controller.advice;

import br.com.symon.userbase.model.api.ApiErrorResponse;
import br.com.symon.userbase.model.api.ValidationError;
import br.com.symon.userbase.model.api.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Log4j2
public class HibernateValidatorsControllerAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<ApiErrorResponse>> handleNotFoundException(WebExchangeBindException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        for (ObjectError error: ex.getAllErrors()) {
            errorResponse.errors().add(ValidationError.fromErrorMessage(error.getDefaultMessage()));
        }
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

    @ExceptionHandler(ValidationError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<ApiErrorResponse>> handleValidationException(ValidationError ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.errors().add(ex);
        return Mono.just(ResponseEntity.badRequest().body(errorResponse));
    }

}