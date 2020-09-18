package com.example.nasa.request;

import com.example.nasa.RoverName;
import org.glassfish.jersey.internal.guava.Maps;

import java.util.HashMap;

public class MarsRoverPhotoRequest {

    private final RoverName rover;
    private HashMap<String, Object> queryParams = Maps.newHashMapWithExpectedSize(4);

    public MarsRoverPhotoRequest(RoverName rover, HashMap<String, Object> queryParams) {
        this.rover = rover;
        this.queryParams = queryParams;
    }

    public HashMap<String, Object> getQueryParams() {
        return queryParams;
    }

    public RoverName getRover() {
        return rover;
    }
}
