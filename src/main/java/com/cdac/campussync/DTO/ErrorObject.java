package com.cdac.campussync.DTO;

public class ErrorObject {
    String message;

    //getter, setter, constructor
    public ErrorObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
