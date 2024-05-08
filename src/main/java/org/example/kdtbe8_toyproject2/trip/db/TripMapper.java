package org.example.kdtbe8_toyproject2.trip.db;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TripMapper {
    List<TripListEntity> getList();
    GetTripByIdEntity getById(int id);
    void create(TripEntity trip);
    int update(TripEntity trip);
    int delete(Long TripId);
}
