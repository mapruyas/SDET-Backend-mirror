package com.example.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Rover implements Serializable {
    private Integer id;
    private String name;
    @JsonProperty("landing_date")
    private String landingDate;
    @JsonProperty("launch_date")
    private String launchDate;
    private String status;

    public Rover(Integer id, String name, String landingDate, String launchDate, String status) {
        this.id = id;
        this.name = name;
        this.landingDate = landingDate;
        this.launchDate = launchDate;
        this.status = status;
    }

    public Rover() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLandingDate() {
        return landingDate;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Rover)) {
            return false;
        }

        Rover other = (Rover) o;

        return id.equals(other.id)
                && name.equals(other.getName())
                && landingDate.equals(other.getLandingDate())
                && launchDate.equals(other.getLaunchDate())
                && status.equals(other.getStatus());
    }
}
