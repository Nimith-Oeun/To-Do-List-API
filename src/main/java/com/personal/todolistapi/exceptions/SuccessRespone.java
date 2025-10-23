package com.personal.todolistapi.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class SuccessRespone {

    public static ExceptionResponeDTO buildResponse(
            String errorCode,
            String status,
            String message,
            Object data,
            boolean success,
            HttpServletRequest request
    ) {
        return ExceptionResponeDTO.builder()
                .success(success)
                .errorCode(errorCode)
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(data)
                .path(request.getRequestURI()) // ✅ correct method
                .build();
    }

    public static ExceptionResponeDTO success(
            Object data,
            HttpServletRequest request
    ) {
        return buildResponse(
                "200",
                "Ok",
                "Success",
                data,
                true,
                request
        );
    }
}