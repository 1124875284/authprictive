package com.hzq.exception;

public class UserNotException extends RuntimeException {

    public UserNotException(String id){
        super("user not exit");
        this.id=id;
    }
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
