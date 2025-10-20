package com.personal.todolistapi.exceptions;


public class BadRequestException extends RuntimeException {

   public BadRequestException(String message) {
      super(message);

   }

}
