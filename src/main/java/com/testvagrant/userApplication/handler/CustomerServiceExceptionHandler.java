package com.testvagrant.userApplication.handler;

import com.testvagrant.userApplication.dto.APIResponse;
import com.testvagrant.userApplication.dto.ErrorDTO;
import com.testvagrant.userApplication.exception.CustomerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomerServiceExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleRequestPathValidationException(Exception ex, HttpServletRequest request){
        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("timestamp",new Date());
        errorMap.put("status",HttpStatus.BAD_REQUEST);
        errorMap.put("error",ex.getMessage());
        errorMap.put("path",request.getServletPath());
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO(errorMap,"")));
        return serviceResponse;
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public APIResponse<?> handleProductNotFoundException(CustomerNotFoundException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }
}
