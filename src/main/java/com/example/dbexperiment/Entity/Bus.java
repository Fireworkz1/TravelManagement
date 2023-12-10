package com.example.dbexperiment.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Bus {
    private String location;
    private Integer price;
    private Integer numBus;
    private Integer numAvail;
    private Date time;


}
