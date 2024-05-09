package org.example.kdtbe8_toyproject2.itinerary.db;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItineraryMapper {
    List<ItineraryEntity> findAllItineraries(Long tripId);
    MoveEntity findMoveById(Long itineraryId);
    StayEntity findStayById(Long itineraryId);
    ItineraryEntity findItineraryById(Long itineraryId);

    int createItinerary(ItineraryEntity itinerary);
    int createMove(MoveEntity move);
    int createStay(StayEntity stay);

    int updateItinerary(ItineraryEntity itinerary);

    int deleteItinerary(Long itineraryId);
    int deleteMove(Long itineraryId);
    int deleteStay(Long itineraryId);


}
