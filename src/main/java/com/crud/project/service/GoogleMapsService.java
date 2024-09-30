package com.crud.project.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {
    private final GeoApiContext context;

    public GoogleMapsService(@Value("${google.maps.api.key}") String apiKey) {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public long calculateDistance(String origin, String destination) throws Exception {
        DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                .origins(origin)
                .destinations(destination)
                .mode(TravelMode.DRIVING)
                .await();

        return matrix.rows[0].elements[0].distance.inMeters;
    }
}