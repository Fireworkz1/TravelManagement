package com.example.dbexperiment.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Flight {
    private String flightNum;
    private Integer price;
    private Integer numSeats;
    private Integer numAvail;
    private String  fromCity;
    private String  arivCity;
    private Date time;

}
