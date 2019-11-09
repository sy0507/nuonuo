package com.example.nuonuo.exception;

public class UserLoginIncorrectException extends RuntimeException{
  private String account;
  private String password;

  public UserLoginIncorrectException(String message, String account, String password) {
    super(message);
    this.account = account;
    this.password = password;
  }

  public UserLoginIncorrectException(String message, Throwable cause, String account, String password) {
    super(message, cause);
    this.account = account;
    this.password = password;
  }

  public UserLoginIncorrectException(String message) {
    super(message);
  }
}
