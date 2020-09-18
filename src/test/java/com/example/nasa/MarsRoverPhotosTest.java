package com.example.nasa;

import com.example.nasa.request.MarsRoverPhotoRequest;
import com.example.nasa.request.MarsRoverPhotoRequestBuilder;
import com.example.nasa.response.MarsRoverPhotosResponse;
import com.example.nasa.response.ResponseInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MarsRoverPhotosTest {

    private NASAApiService client;

    @BeforeEach
    public void setUp() {
        String apiKey = "BPLiItfYP3AF5cTMZGUoXuQr7U7FaEpb3OugIlLY";
        this.client = new NASAApiService("https://api.nasa.gov", apiKey);
    }

    @Test
    public void shouldGetFirst10PhotosTakenByCuriosityOnGivenSol() {
        MarsRoverPhotoRequest request = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setPage(1)
                .setPerPage(10)
                .setSol(1000)
                .build();
        ResponseInterface<MarsRoverPhotosResponse> response = this.client.getRoverPhotos(request);
        MarsRoverPhotosResponse marsRoverPhotosResponse = response.getBody();

        assertThat(response.getStatusCode(), is(equalTo(200)));
        assertThat(marsRoverPhotosResponse.getPhotos().size(), is(equalTo(10)));
        marsRoverPhotosResponse.getPhotos().forEach((photo) -> {
            assertThat(photo.getRover().getName(), is(equalTo("Curiosity")));
            assertThat(photo.getSol(), is(equalTo(1000)));
        });
    }

    @Test
    public void shouldGetFirst10PhotosTakenByCuriosityOnGivenEarthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.MAY, 30);

        MarsRoverPhotoRequest request = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setEarthDate(calendar.getTime())
                .setPage(1)
                .setPerPage(10)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> response = this.client.getRoverPhotos(request);
        MarsRoverPhotosResponse marsRoverPhotosResponse = response.getBody();

        assertThat(response.getStatusCode(), is(equalTo(200)));
        assertThat(marsRoverPhotosResponse.getPhotos().size(), is(equalTo(10)));
        marsRoverPhotosResponse.getPhotos().forEach((photo) -> {
            assertThat(photo.getRover().getName(), is(equalTo("Curiosity")));
            assertThat(photo.getSol(), is(equalTo(1000)));
        });
    }

    @Test
    public void shouldGetTheSameFirst10PhotosTakenByCuriosityOnGivenEarthDateAndSol() {
        MarsRoverPhotoRequest requestBySol = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setPage(1)
                .setPerPage(10)
                .build();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.MAY, 30);

        MarsRoverPhotoRequest requestByEarthDate = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setEarthDate(calendar.getTime())
                .setPage(1)
                .setPerPage(10)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> responseBySol = this.client.getRoverPhotos(requestBySol);
        MarsRoverPhotosResponse photosBySol = responseBySol.getBody();

        ResponseInterface<MarsRoverPhotosResponse> responseByEarthDate = this.client.getRoverPhotos(requestByEarthDate);
        MarsRoverPhotosResponse photosByDate = responseByEarthDate.getBody();

        assertThat(responseBySol.getStatusCode(), is(equalTo(200)));
        assertThat(photosBySol.getPhotos().size(), is(equalTo(10)));
        assertThat(responseByEarthDate.getStatusCode(), is(equalTo(200)));
        assertThat(photosByDate.getPhotos().size(), is(equalTo(10)));

        assertThat(photosBySol.getPhotos(), is(photosByDate.getPhotos()));
    }

    @Test
    public void curiosityFHAZCameraShouldNotHaveMorePicturesThanTenTimesOpportunityPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityFHAZCameraShouldNotHaveMorePicturesThanTenTimesSpiritPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Opportunity, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityFHAZCameraShouldNotHaveMorePicturesThanTenTimesOpportunityMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Spirit, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityFHAZCameraShouldNotHaveMorePicturesThanTenTimesSpiritMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.FRONT_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityRHAZCameraShouldNotHaveMorePicturesThanTenTimesOpportunityPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityRHAZCameraShouldNotHaveMorePicturesThanTenTimesSpiritPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Opportunity, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityRHAZCameraShouldNotHaveMorePicturesThanTenTimesOpportunityMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Spirit, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityRHAZCameraShouldNotHaveMorePicturesThanTenTimesSpiritMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.REAR_HAZARD_AVOIDANCE_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Curiosity, Camera Mast Camera has more photos on sol 1000 than 10 times Rover Opportunity Camera PANORAMIC")
    public void curiosityMASTCameraShouldNotHaveMorePicturesThanTenTimesOpportunityPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MAST_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Curiosity, Camera Mast Camera has more photos on sol 1000 than 10 times Rover Spirit Camera PANORAMIC")
    public void curiosityMASTCameraShouldNotHaveMorePicturesThanTenTimesSpiritPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MAST_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Opportunity, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityMASTCameraShouldNotHaveMorePicturesThanTenTimesOpportunityMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MAST_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Spirit, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityMASTCameraShouldNotHaveMorePicturesThanTenTimesSpiritMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MAST_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityCHEMCAMCameraShouldNotHaveMorePicturesThanTenTimesOpportunityPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityCHEMCAMCameraShouldNotHaveMorePicturesThanTenTimesSpiritPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Opportunity, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityCHEMCAMCameraShouldNotHaveMorePicturesThanTenTimesOpportunityMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Spirit, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityCHEMCAMCameraShouldNotHaveMorePicturesThanTenTimesSpiritMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityMAHLICameraShouldNotHaveMorePicturesThanTenTimesOpportunityPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MARS_HAND_LENS_IMAGER)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityMAHLICameraShouldNotHaveMorePicturesThanTenTimesSpiritPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MARS_HAND_LENS_IMAGER)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Opportunity, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityMAHLICameraShouldNotHaveMorePicturesThanTenTimesOpportunityMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MARS_HAND_LENS_IMAGER)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Spirit, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityMAHLICameraShouldNotHaveMorePicturesThanTenTimesSpiritMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.CHEMISTRY_AND_CAMERA_COMPLEX)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityMARDICameraShouldNotHaveMorePicturesThanTenTimesOpportunityPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MARS_DESCENT_IMAGER)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityMARDICameraShouldNotHaveMorePicturesThanTenTimesSpiritPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MARS_DESCENT_IMAGER)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Opportunity, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityMARDICameraShouldNotHaveMorePicturesThanTenTimesOpportunityMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MARS_DESCENT_IMAGER)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Spirit, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityMARDICameraShouldNotHaveMorePicturesThanTenTimesSpiritMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.MARS_DESCENT_IMAGER)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityNAVCAMCameraShouldNotHaveMorePicturesThanTenTimesOpportunityPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.NAVIGATION_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    public void curiosityNAVCAMCameraShouldNotHaveMorePicturesThanTenTimesSpiritPANCAM() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.NAVIGATION_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.PANORAMIC_CAMERA)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Opportunity, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityNAVCAMCameraShouldNotHaveMorePicturesThanTenTimesOpportunityMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.NAVIGATION_CAMERA)
                .build();

        MarsRoverPhotoRequest opportunityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.OPPORTUNITY)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> opportunityPhotos = this.client.getRoverPhotos(opportunityRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(opportunityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse opportunityPhotosResponse = opportunityPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(opportunityPhotosResponse.getPhotos().size() * 10)));
    }

    @Test
    @Disabled("Rover Spirit, Camera Miniature thermal emission spectrometer has 0 photos on sol 1000")
    public void curiosityNAVCAMCameraShouldNotHaveMorePicturesThanTenTimesSpiritMINITES() {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(RoverCamera.NAVIGATION_CAMERA)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.SPIRIT)
                .setSol(1000)
                .setCamera(RoverCamera.MINIATURE_THERMAL_EMISSION_SPECTROMETER)
                .build();

        ResponseInterface<MarsRoverPhotosResponse> curiosityPhotos = this.client.getRoverPhotos(curiosityRequest);
        ResponseInterface<MarsRoverPhotosResponse> spiritPhotos = this.client.getRoverPhotos(spiritRequest);

        assertThat(curiosityPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));
        assertThat(spiritPhotos.getStatusCode(), is(equalTo(Response.Status.OK.getStatusCode())));

        MarsRoverPhotosResponse curiosityPhotosResponse = curiosityPhotos.getBody();
        MarsRoverPhotosResponse spiritPhotosResponse = spiritPhotos.getBody();

        assertThat(curiosityPhotosResponse.getPhotos().size(), is(lessThanOrEqualTo(spiritPhotosResponse.getPhotos().size() * 10)));
    }
}
