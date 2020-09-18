package com.example.nasa;

public class Routes {

    private static final String MARS_ROVER_PHOTOS_PATH = "/mars-photos/api/v1/rovers/%s/photos";

    public static String getMarsRoverPhotosPathForRover(RoverName rover) {
        return String.format(MARS_ROVER_PHOTOS_PATH, rover.toString());
    }
}
