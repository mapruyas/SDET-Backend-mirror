package com.example.nasa.request;

import com.example.nasa.RoverCamera;
import com.example.nasa.RoverName;
import org.glassfish.jersey.internal.guava.Maps;

import java.util.HashMap;

public class MarsRoverPhotoRequestBuilder {

    private RoverName rover;
    HashMap<String, Object> parameters = new HashMap<>();

    public MarsRoverPhotoRequestBuilder() {}

    public MarsRoverPhotoRequestBuilder setRover(RoverName name) {
        this.rover = name;
        return this;
    }

    public MarsRoverPhotoRequestBuilder setSol(Integer sol) {
        parameters.put("sol", sol);
        return this;
    }

    public MarsRoverPhotoRequestBuilder setEarthDate(String earthDate) {
        parameters.put("earth_date", earthDate);
        return this;
    }

    public MarsRoverPhotoRequestBuilder setCamera(RoverCamera camera) {
        parameters.put("camera", camera.toString());
        return this;
    }

    public MarsRoverPhotoRequestBuilder setPage(Integer page) {
        parameters.put("page", page);
        return this;
    }

    public MarsRoverPhotoRequestBuilder setPerPage(Integer perPage) {
        parameters.put("per_page", perPage);
        return this;
    }

    public MarsRoverPhotoRequest build() {
        return new MarsRoverPhotoRequest(this.rover, this.parameters);
    }
}
