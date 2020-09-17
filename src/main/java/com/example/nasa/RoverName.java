package com.example.nasa;

public enum RoverName {

    CURIOSITY("curiosity"),
    OPPORTUNITY("opportunity"),
    SPIRIT("spirit");

    private String roverName;

    RoverName(String name) {
        this.roverName = name;
    }

    @Override
    public String toString() {
        return roverName;
    }
}
