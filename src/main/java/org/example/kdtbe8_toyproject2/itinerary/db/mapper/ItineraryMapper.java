package org.example.kdtbe8_toyproject2.itinerary.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.StayEntity;

import java.util.List;

@Mapper
public interface ItineraryMapper {
    List<ItineraryEntity> findAllItinerary();
    List<MoveEntity> findAllMoves();
    List<StayEntity> findAllStays();

    public Long createItinerary(ItineraryEntity itinerary);
    public int createMove(MoveEntity move);
    public int createStay(StayEntity stay);

    public void deleteItinerary(Long itineraryId);
    public void deleteMove(Long itineraryId);
    public void deleteStay(Long itineraryId);

}
