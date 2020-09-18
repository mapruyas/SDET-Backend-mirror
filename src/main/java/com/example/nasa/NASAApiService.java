package com.example.nasa;

import com.example.nasa.request.MarsRoverPhotoRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class NASAApiService {

    private final String baseUrl;
    private final String apiKey;
    private final Client client;

    public NASAApiService(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.client = ClientBuilder.newClient();
    }

    public Response getRoverPhotos(MarsRoverPhotoRequest request) {
        WebTarget webTarget = this.client
                .target(baseUrl)
                .path(Routes.getMarsRoverPhotosPathForRover(request.getRover()))
                .queryParam("api_key", this.apiKey);

        for (Map.Entry<String, Object> queryParam: request.getQueryParams().entrySet()) {
            webTarget = webTarget.queryParam(queryParam.getKey(), queryParam.getValue());
        }

        return webTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
    }
}
