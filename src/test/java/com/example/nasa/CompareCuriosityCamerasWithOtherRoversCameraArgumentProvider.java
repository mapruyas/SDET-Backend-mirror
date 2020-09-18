package com.example.nasa;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CompareCuriosityCamerasWithOtherRoversCameraArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        (RoverCamera) RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MAST_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MAST_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_HAND_LENS_IMAGER,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_HAND_LENS_IMAGER,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_DESCENT_IMAGER,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_DESCENT_IMAGER,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.NAVIGATION_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.NAVIGATION_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MAST_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MAST_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_HAND_LENS_IMAGER,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_HAND_LENS_IMAGER,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_DESCENT_IMAGER,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.MARS_DESCENT_IMAGER,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.NAVIGATION_CAMERA,
                        (RoverName) RoverName.OPPORTUNITY,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                ),
                Arguments.of(
                        (RoverCamera) RoverCamera.NAVIGATION_CAMERA,
                        (RoverName) RoverName.SPIRIT,
                        (RoverCamera) RoverCamera.PANORAMIC_CAMERA
                )
        );
    }
}
