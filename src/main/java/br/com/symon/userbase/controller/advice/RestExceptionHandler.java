package br.com.symon.userbase.controller.advice;

import br.com.symon.userbase.model.api.ApiError;
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
public class RestExceptionHandler {


    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseEntity<ApiResponse>> handleNotFoundException(WebExchangeBindException ex) {
        ApiResponse apiResponse = ApiResponse.builder().build();
        for (ObjectError error: ex.getAllErrors()) {
            apiResponse.getErrors().add(ApiError.builder().message(error.getDefaultMessage()).build());
        }
        return Mono.just(ResponseEntity.badRequest().body(apiResponse));
    }

}