package com.example.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RoverPhoto implements Serializable {

    private Integer id;
    private Integer sol;
    @JsonProperty("img_src")
    private String imageSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    private RoverCamera camera;
    private Rover rover;

    public RoverPhoto(Integer id, Integer sol, String imageSrc, String earthDate, RoverCamera camera, Rover rover) {
        this.id = id;
        this.sol = sol;
        this.imageSrc = imageSrc;
        this.earthDate = earthDate;
        this.camera = camera;
        this.rover = rover;
    }

    public RoverPhoto() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getSol() {
        return sol;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public RoverCamera getCamera() {
        return camera;
    }

    public Rover getRover() {
        return rover;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof RoverPhoto)) {
            return false;
        }

        RoverPhoto other = (RoverPhoto) o;

        return id.equals(other.id)
                && sol.equals(other.getSol())
                && imageSrc.equals(other.getImageSrc())
                && earthDate.equals(other.getEarthDate())
                && rover.equals(other.getRover())
                && camera.equals(other.getCamera());
    }
}
