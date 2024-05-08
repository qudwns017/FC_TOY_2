package org.example.kdtbe8_toyproject2.itinerary.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.entity.StayEntity;

import java.util.List;

@Mapper
public interface ItineraryMapper {
    List<ItineraryEntity> findAllItineraries(Long tripId);
    MoveEntity findMoveById(Long itineraryId);
    StayEntity findStayById(Long itineraryId);

    public Long createItinerary(ItineraryEntity itinerary);
    public int createMove(MoveEntity move);
    public int createStay(StayEntity stay);

    public int deleteItinerary(Long itineraryId);
    public int deleteMove(Long itineraryId);
    public int deleteStay(Long itineraryId);

    public ItineraryEntity findItineraryById(Long itineraryId);

    public int updateItinerary(ItineraryEntity itinerary);

}
