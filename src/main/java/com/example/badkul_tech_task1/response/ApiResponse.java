package com.example.badkul_tech_task1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Locked;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private int status;
    private LocalDateTime dateTime;
}
