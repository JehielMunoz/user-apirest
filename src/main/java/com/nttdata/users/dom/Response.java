package com.nttdata.users.dom;

public class Response<T> {

  private String message;

  public Response() {}

  public Response(String message) {
    this.message = message;
  }

  /**
   * @return the errMessage
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param errMessage the errMessage to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

}
