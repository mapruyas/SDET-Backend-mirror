package com.example.nasa.response;

import com.example.nasa.model.RoverPhoto;

import java.io.Serializable;
import java.util.ArrayList;

public class MarsRoverPhotosResponse implements Serializable {

    private ArrayList<RoverPhoto> photos;

    public MarsRoverPhotosResponse(ArrayList<RoverPhoto> photos) {
        this.photos = photos;
    }

    public MarsRoverPhotosResponse() {}

    public ArrayList<RoverPhoto> getPhotos() {
        return photos;
    }
}
