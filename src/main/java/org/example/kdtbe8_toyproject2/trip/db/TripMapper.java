package org.example.kdtbe8_toyproject2.trip.db;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.example.kdtbe8_toyproject2.trip.model.TripAccommodationDto;
import org.example.kdtbe8_toyproject2.trip.model.TripListDto;

@Mapper
public interface TripMapper {
    List<TripListDto> getTripList();
    TripAccommodationDto getTripById(int id);
}
