package com.gokul.cab_booking.Cab.Booking.services.impl;

import com.gokul.cab_booking.Cab.Booking.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
        String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
        try {
            OSRMResDTO responseDTO = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResDTO.class);

            return responseDTO.getRoutes().get(0).getDistance() / 1000.0;
        }catch(Exception e){
            throw new RuntimeException("Error Getting Data from OSRM"+ e.getMessage());
        }
    }
}

@Data
class OSRMResDTO{

    private List<OSRMRoute> routes;


}

@Data
class OSRMRoute{
    private Double distance;
}
