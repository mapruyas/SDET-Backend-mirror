package com.example.nasa.response;

import javax.ws.rs.core.Response;

public class RestResponse<T> implements ResponseInterface<T> {
    private Class<T> bodyClass;
    private Response response;

    public RestResponse(Class<T> bodyClass, Response response) {
        this.response = response;
        this.bodyClass = bodyClass;
    }

    @Override
    public T getBody() {
        return response.readEntity(bodyClass);
    }

    @Override
    public String getContent() {
        return response.readEntity(String.class);
    }

    @Override
    public int getStatusCode() {
        return response.getStatus();
    }

    @Override
    public Response getResponse() {
        return response;
    }
}
