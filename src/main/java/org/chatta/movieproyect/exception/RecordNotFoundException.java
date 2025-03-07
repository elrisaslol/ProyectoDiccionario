package org.chatta.movieproyect.exception;

public class RecordNotFoundException extends RuntimeException {

  private String exceptionMessage;
  private Object fieldValue;


  public RecordNotFoundException(String exceptionMessage,Object fieldValue) {
      super(exceptionMessage + " - " + fieldValue);
      this.exceptionMessage = exceptionMessage;
      this.fieldValue = fieldValue;
  }
  public String getExceptionMessage() {
    return exceptionMessage;
  }
  public Object getFieldValue() {
    return fieldValue;
 }



}
