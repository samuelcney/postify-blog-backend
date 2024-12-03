package com.spring.app.postify.utils;

public class ApiResponse {

    private String message;
    private String status;
    private Object data;

    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}