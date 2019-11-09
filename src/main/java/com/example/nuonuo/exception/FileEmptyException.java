package com.example.nuonuo.exception;

public class FileEmptyException extends CommonException{
  public FileEmptyException(String message) {
    super(message);
  }

  public FileEmptyException(String message, Throwable cause) {
    super(message, cause);
  }
}
