package org.example.kdtbe8_toyproject2.accommodation.db;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AccommodationMapper {
    List<AccommodationEntity> findByTripId(Long tripId);

    int create(AccommodationEntity accommodationEntity);

    int delete(Long id);
}
