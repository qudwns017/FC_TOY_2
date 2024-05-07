package org.example.accomodation.service;

import lombok.RequiredArgsConstructor;
import org.example.accomodation.db.AccomodationEntity;
import org.example.accomodation.db.AccomodationMapper;
import org.example.accomodation.model.AccomodationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccomodationService {
    private final AccomodationMapper accomodationMapper;
    //private final TripMapper tripMapper;

    public AccomodationEntity create(AccomodationRequest accomodationRequest) {
        return accomodationMapper.create(accomodationRequest);
    }

    public void delete(Long tripId, Long accomodationId) {
         accomodationMapper.delete(tripId,accomodationId);
    }

    public List<AccomodationEntity> findByTripId(Long tripId, Long accomodationId){
        return accomodationMapper.findByTripId(tripId, accomodationId);
    }
}
/*
create()
findByTripId()
delete()
 */