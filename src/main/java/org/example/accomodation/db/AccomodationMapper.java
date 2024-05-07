package org.example.accomodation.db;

import org.example.accomodation.model.AccomodationRequest;

import java.util.List;

public interface AccomodationMapper {
    public void delete(Long tripId, Long accomodationId);
    public List<AccomodationEntity> findByTripId(Long tripId, Long accomodationId);
    public AccomodationEntity create(AccomodationRequest accomodationRequest);  //void
}
