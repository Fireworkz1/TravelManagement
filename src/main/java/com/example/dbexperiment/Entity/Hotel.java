package com.example.dbexperiment.Entity;

import lombok.Data;

@Data
public class Hotel {
    private String location;
    private Integer price;
    private Integer numRooms;
    private Integer numAvail;
}
