package org.example.kdtbe8_toyproject2.itinerary.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.example.kdtbe8_toyproject2.itinerary.db.ItineraryEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.MoveEntity;
import org.example.kdtbe8_toyproject2.itinerary.db.StayEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ItineraryDto {

    private Long id;
    private Long tripId;
    private String name;
    private Integer type;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String comment;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) private String transportation;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) private String departurePlace;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) private String arrivalPlace;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) private String place;

    public static ItineraryDto toDto(ItineraryEntity itineraryEntity, MoveEntity moveEntity, StayEntity stayEntity) {
        return ItineraryDto.builder()
                .id(itineraryEntity.getId())
                .tripId(itineraryEntity.getTripId())
                .name(itineraryEntity.getName())
                .type(itineraryEntity.getType())
                .startDatetime(itineraryEntity.getStartDatetime())
                .endDatetime(itineraryEntity.getEndDatetime())
                .comment(itineraryEntity.getComment())
                .transportation(moveEntity != null ? moveEntity.getTransportation() : null)
                .departurePlace(moveEntity != null ? moveEntity.getDeparturePlace() : null)
                .arrivalPlace(moveEntity != null ? moveEntity.getArrivalPlace() : null)
                .place(stayEntity != null ? stayEntity.getPlace() : null)
                .build();
    }
}
