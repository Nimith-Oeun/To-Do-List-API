package com.personal.todolistapi.exceptions;


public class CustomMaxUploadSizeExceededException extends RuntimeException {

   public CustomMaxUploadSizeExceededException(String message) {
      super(message);

   }

}