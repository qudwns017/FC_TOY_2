package org.example.accomodation.db;

import org.example.accomodation.model.AccomodationDTO;

import java.util.List;

public interface AccomodationMapper {
    public void delete(Long tripId, Long accomodationId);
   // public List<AccomodationEntity> findByTripId(Long tripId, Long accomodationId);
    public int create(AccomodationEntity accomodationEntity);
    public List<AccomodationEntity> findByTripId(Long tripId);
    AccomodationDTO toAccomodationDto(List<AccomodationEntity> entity);
}
