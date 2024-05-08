package org.example.kdtbe8_toyproject2.trip.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.trip.db.TripEntity;
import org.example.kdtbe8_toyproject2.trip.db.TripMapper;
import org.example.kdtbe8_toyproject2.trip.model.TripDto;
import org.example.kdtbe8_toyproject2.trip.model.TripRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripMapper tripMapper;
    private final TripConverter tripConverter;

    public TripDto create(TripRequest tripRequest) {
        var entity = TripEntity.builder()
                .tripName(tripRequest.getTripName())
                .startDate(tripRequest.getStartDate())
                .endDate(tripRequest.getEndDate())
                .isOversea(tripRequest.getIsOversea())
                .comment(tripRequest.getComment())
                .build();

        tripMapper.create(entity);

        return tripConverter.toDto(entity);
    }

    public int update(Long tripId, TripRequest tripRequest) {
        var entity = TripEntity.builder()
                .tripId(tripId)
                .tripName(tripRequest.getTripName())
                .startDate(tripRequest.getStartDate())
                .endDate(tripRequest.getEndDate())
                .isOversea(tripRequest.getIsOversea())
                .comment(tripRequest.getComment())
                .build();

        return tripMapper.update(entity);
    }

    public int delete(Long tripId) {
        return tripMapper.delete(tripId);
    }
}
