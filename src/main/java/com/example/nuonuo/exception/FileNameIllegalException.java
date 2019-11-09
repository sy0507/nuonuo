package com.example.nuonuo.exception;

public class FileNameIllegalException extends  RuntimeException {
  public FileNameIllegalException(String message) {
    super(message);
  }

  public FileNameIllegalException(String message, Throwable cause) {
    super(message, cause);
  }
}
