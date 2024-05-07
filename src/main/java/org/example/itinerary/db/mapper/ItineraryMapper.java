package org.example.itinerary.db.mapper;

import org.example.itinerary.db.entity.ItineraryEntity;
import org.example.itinerary.db.entity.MoveEntity;
import org.example.itinerary.db.entity.StayEntity;

import java.util.List;

public interface ItineraryMapper {
    List<ItineraryEntity> findAllItinerary();
    List<MoveEntity> findAllMoves();
    List<StayEntity> findAllStays();
}
