package org.example.kdtbe8_toyproject2.trip.db;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.example.kdtbe8_toyproject2.trip.model.TripByIdDto;
import org.example.kdtbe8_toyproject2.trip.model.TripListDto;

@Mapper
public interface TripMapper {
    List<TripListDto> getTripList();
    TripByIdDto getTripById(int id); // 반환 자료형 수정 필요
}
