package kr.intraview.exception;

public class EmailNotFoundException extends Exception {

  public EmailNotFoundException(String message) {
    super(message);
  }

  public EmailNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
