package com.personal.todolistapi.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class SuccessRespone {

    public static ExceptionResponeDTO buildResponse(
            String errorCode,
            String status,
            String message,
            Object responeData,
            boolean success,
            HttpServletRequest request
    ) {
        return ExceptionResponeDTO.builder()
                .success(success)
                .errorCode(errorCode)
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .responeData(responeData)
                .path(request.getRequestURI()) // ✅ correct method
                .build();
    }

    public static ExceptionResponeDTO success(
            Object responeData,
            HttpServletRequest request
    ) {
        return buildResponse(
                "200",
                "Ok",
                "Success",
                responeData,
                true,
                request
        );
    }
}