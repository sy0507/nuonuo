package com.example.nuonuo.exception;

public class FileStoreFailedException extends RuntimeException {
  public FileStoreFailedException(String message) {
    super(message);
  }

  public FileStoreFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
