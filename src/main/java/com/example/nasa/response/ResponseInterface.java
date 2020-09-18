package com.example.nasa.response;

import javax.ws.rs.core.Response;

public interface ResponseInterface<T> {

    T getBody();

    String getContent();

    int getStatusCode();

    Response getResponse();
}
