package com.testvagrant.userApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String field;
    private String errorMessage;
    private Map<String,Object> errorMap;

    public ErrorDTO(Map<String, Object> errorMap,String field) {
        this.errorMap = errorMap;
        this.field = field;
    }

    public ErrorDTO(String field ,String errorMessage) {
        this.errorMessage = errorMessage;
        this.field = field;
    }
}