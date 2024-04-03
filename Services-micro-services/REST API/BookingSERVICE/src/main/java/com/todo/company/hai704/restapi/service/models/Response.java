package com.todo.company.hai704.restapi.service.models;

public class Response {
    private int code;
    private String explaination;

    private String token;

    private Object object;

    public Response() {
    }

    public Response(int code, String explaination) {
        this.code = code;
        this.explaination = explaination;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
