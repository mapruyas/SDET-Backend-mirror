package com.example.nasa;

import com.example.nasa.request.MarsRoverPhotoRequest;
import com.example.nasa.request.MarsRoverPhotoRequestBuilder;
import com.example.nasa.response.MarsRoverPhotosResponse;
import com.example.nasa.response.ResponseInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

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

    @ParameterizedTest
    @ArgumentsSource(CompareCuriosityCamerasWithOtherRoversCameraArgumentProvider.class)
    public void curiosityCamerasShouldNotHaveMorePicturesThanTenTimesOtherRoversCameras(
            RoverCamera curiosityCamera, RoverName roverToCompareName, RoverCamera otherRoverCamera
    ) {
        MarsRoverPhotoRequest curiosityRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(RoverName.CURIOSITY)
                .setSol(1000)
                .setCamera(curiosityCamera)
                .build();

        MarsRoverPhotoRequest spiritRequest = new MarsRoverPhotoRequestBuilder()
                .setRover(roverToCompareName)
                .setSol(1000)
                .setCamera(otherRoverCamera)
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
