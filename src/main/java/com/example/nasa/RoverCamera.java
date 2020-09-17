package com.example.nasa;

public enum RoverCamera {

    FRONT_HAZARD_AVOIDANCE_CAMERA("fhaz"),
    REAR_HAZARD_AVOIDANCE_CAMERA("rhaz"),
    MAST_CAMERA("mast"),
    CHEMISTRY_AND_CAMERA_COMPLEX("chemcam"),
    MARS_HAND_LENS_IMAGER("mahli"),
    MARS_DESCENT_IMAGER("mardi"),
    NAVIGATION_CAMERA("navcam"),
    PANORAMIC_CAMERA("pancam"),
    MINIATURE_THERMAL_EMISSION_SPECTROMETER("minites");

    private String cameraName;

    RoverCamera(String cameraName) {
        this.cameraName = cameraName;
    }

    @Override
    public String toString() {
        return cameraName;
    }
}
