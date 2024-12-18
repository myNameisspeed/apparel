package com.example.demo.apparel.dto;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDTO {
    private HttpStatus status;
    private String message;
    private Object data;
    
    
    public ResponseDTO() {

    }
    public ResponseDTO(Object data) {
        this.status = HttpStatus.OK;
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    public ResponseDTO(Object data, HttpHeaders headers) {
        this.status = HttpStatus.OK;
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    public ResponseDTO(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(HttpStatus status, String message, HttpHeaders headers, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
