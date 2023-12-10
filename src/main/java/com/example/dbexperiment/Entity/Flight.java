package com.example.dbexperiment.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Flight {
    private String flightNum;
    private Integer price;
    private Integer numSeats;
    private Integer numAvail;
    private String  fromCity;
    private String  arivCity;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;

}
