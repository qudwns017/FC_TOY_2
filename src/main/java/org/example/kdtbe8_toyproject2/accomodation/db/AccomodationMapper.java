package org.example.kdtbe8_toyproject2.accomodation.db;

import org.apache.ibatis.annotations.Mapper;
import org.example.kdtbe8_toyproject2.accomodation.model.AccomodationDTO;

import java.util.List;
@Mapper
public interface AccomodationMapper {
    public void delete(Long tripId, Long accomodationId);
    public int create(AccomodationEntity accomodationEntity);
    AccomodationDTO toAccomodationDto(List<AccomodationEntity> entity);
    public List<AccomodationEntity> findByTripId(Long tripId);

}
