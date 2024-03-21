package com.nttdata.users.dom;

public class ErrorResponse extends RuntimeException {

  private final String status;
  private final String message;

  public ErrorResponse(String string, String message) {
    super(message);
    this.status = string;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
