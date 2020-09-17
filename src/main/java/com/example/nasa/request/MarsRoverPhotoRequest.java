package com.example.nasa.request;

import org.glassfish.jersey.internal.guava.Maps;

import java.util.HashMap;

public class MarsRoverPhotoRequest {

    private final String rover;
    private HashMap<String, Object> queryParams = Maps.newHashMapWithExpectedSize(4);

    public MarsRoverPhotoRequest(String rover, HashMap<String, Object> queryParams) {
        this.rover = rover;
        this.queryParams = queryParams;
    }

    public HashMap<String, Object> getQueryParams() {
        return queryParams;
    }

    public String getRover() {
        return rover;
    }
}
