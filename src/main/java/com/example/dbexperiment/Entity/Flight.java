package com.example.dbexperiment.Entity;

import lombok.Data;

@Data
public class Flight {
    private String flightNum;
    private Integer price;
    private Integer numSeats;
    private Integer numAvail;
    private String  fromCity;
    private String  arivCity;
}
