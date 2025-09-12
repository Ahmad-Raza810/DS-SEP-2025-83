package com.example.badkul_tech_task1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

private String message;
Map<String,String> details=new HashMap<>();
private int statusCode;
private LocalDateTime dateTime;
}
