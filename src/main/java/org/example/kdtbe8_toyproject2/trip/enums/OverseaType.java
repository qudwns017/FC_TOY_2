package org.example.kdtbe8_toyproject2.trip.enums;

import lombok.Getter;

@Getter
public enum OverseaType {
    TRUE("해외", 1),
    FALSE("국내", 0);
    private String s;
    private int value;
    OverseaType(String s, int value){
        this.value = value;
        this.s = s;
    }
}
