package com.codenoted.uploader.service;

public class ImageUploaderException extends RuntimeException {

  private static final long serialVersionUID = -1917414571539105256L;

  /**
   * Constructs a new ImageUploaderException exception with null as its detail message.
   */
  public ImageUploaderException() {
  }

  /**
   * Constructs a new ImageUploaderException exception with the specified detail message.
   * 
   * @param message - the detail message.
   */
  public ImageUploaderException(String message) {
    super(message);
  }

  /**
   * Constructs a new ImageUploaderException exception with the specified detail message and cause.
   * 
   * @param message - the detail message.
   * @param cause - the cause.
   */
  public ImageUploaderException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new ImageUploaderException exception with the specified cause.
   * 
   * @param cause - the cause.
   */
  public ImageUploaderException(Throwable cause) {
    super(cause);
  }

}// ;~
