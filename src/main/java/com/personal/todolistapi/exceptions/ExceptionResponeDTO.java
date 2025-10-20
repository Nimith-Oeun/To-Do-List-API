package com.personal.todolistapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponeDTO {

    private String errorCode;
    private String status;
    private String message;
    private LocalDateTime timestamp;
    private Object responeData;
    private String path;
    private boolean success = false;
}