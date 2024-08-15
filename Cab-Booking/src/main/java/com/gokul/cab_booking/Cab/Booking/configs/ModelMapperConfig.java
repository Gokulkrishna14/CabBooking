package com.gokul.cab_booking.Cab.Booking.configs;

import com.gokul.cab_booking.Cab.Booking.dto.PointDTO;
import com.gokul.cab_booking.Cab.Booking.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(PointDTO.class, Point.class).setConverter(convertor -> {
            PointDTO pointdto = convertor.getSource();
            return GeometryUtil.createPoint(pointdto);
        });

        mapper.typeMap(Point.class, PointDTO.class).setConverter(context -> {
            Point point = context.getSource();
            double coordinates[] = {
                    point.getX(), point.getY()
            };
            return new PointDTO(coordinates);
        });
        return mapper;
    }



}
