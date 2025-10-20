package com.personal.todolistapi.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

public class SuccessRespone {

    public static   ExceptionResponeDTO buildResponse(String errorCode , String status ,
                                                     String message , Object responeData
                                                      ,boolean success
                                                      ){
        return ExceptionResponeDTO.builder()
                .success(success)
                .errorCode(errorCode)
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .responeData(responeData)
                .build();
    }

    public static ExceptionResponeDTO success(Object object){
        return buildResponse(
                "200",
                "Ok",
                "Success",
                object
                ,true


        );
    }
}