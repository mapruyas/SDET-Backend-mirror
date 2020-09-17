package com.example.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RoverCamera implements Serializable {
    private Integer id;
    private String name;
    @JsonProperty("rover_id")
    private Integer roverId;
    @JsonProperty("full_name")
    private String fullName;

    public RoverCamera(Integer id, String name, Integer roverId, String fullName) {
        this.id = id;
        this.name = name;
        this.roverId = roverId;
        this.fullName = fullName;
    }

    public RoverCamera() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRoverId() {
        return roverId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof RoverCamera)) {
            return false;
        }

        RoverCamera other = (RoverCamera) o;

        return id.equals(other.id)
                && name.equals(other.getName())
                && roverId.equals(other.getRoverId())
                && fullName.equals(other.getFullName());
    }
}
