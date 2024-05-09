package org.example.kdtbe8_toyproject2.accommodation.db;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AccommodationMapper {
    public Long delete(Long id);
    public Long create(AccommodationEntity accommodationEntity);
    public List<AccommodationEntity> findByTripId(Long tripId);
}
