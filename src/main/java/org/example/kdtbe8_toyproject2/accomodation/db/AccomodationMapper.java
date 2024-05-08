package org.example.kdtbe8_toyproject2.accomodation.db;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AccomodationMapper {
    public void delete(Long tripId, Long id);
    public Long create(AccomodationEntity accomodationEntity);
    public List<AccomodationEntity> findByTripId(Long tripId);

}
