package com.example.dbexperiment.util.Enum;

import lombok.Getter;

@Getter
public enum SvcTypeEnum {
    flight(1),
    bus(2),
    hotel(3)
    ;
    private final Integer code;
    SvcTypeEnum(Integer i) {
        this.code=i;

    }
}
