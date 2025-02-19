package me.ian.workoutrecoder.exception;

import jakarta.validation.ConstraintViolationException;
import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse handleParamException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return new RestResponse(ErrorCodeEnum.PARAMETER_WRONG.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse handleMissingParameterException(MissingServletRequestParameterException e) {
        return new RestResponse(ErrorCodeEnum.PARAMETER_WRONG.getCode(), e.getMessage());
    }


    @ExceptionHandler({RestException.class})
    public RestResponse handleRestException(RestException e) {
        return new RestResponse(e.getCode(), e.getMsg());
    }
}
