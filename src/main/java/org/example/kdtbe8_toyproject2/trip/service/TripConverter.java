package org.example.kdtbe8_toyproject2.trip.service;

import lombok.RequiredArgsConstructor;
import org.example.kdtbe8_toyproject2.trip.db.TripEntity;
import org.example.kdtbe8_toyproject2.trip.model.TripDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripConverter {
    public TripDto toDto(TripEntity tripEntity) {
        return TripDto.builder()
                .tripId(tripEntity.getTripId())
                .tripName(tripEntity.getTripName())
                .startDate(tripEntity.getStartDate())
                .endDate(tripEntity.getEndDate())
                .isOversea(tripEntity.getIsOversea())
                .comment(tripEntity.getComment())
                .build();
    }
}
