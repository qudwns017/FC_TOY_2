package org.example.kdtbe8_toyproject2.trip.db;

import java.awt.print.Book;
import java.util.List;

public interface TripMapper {
    List<TripEntity> getTripList();
    void getTrip(); // 반환 자료형 수정 필요
}
