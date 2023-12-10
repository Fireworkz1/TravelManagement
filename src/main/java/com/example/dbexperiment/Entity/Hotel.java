package com.example.dbexperiment.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Hotel {
    private String location;
    private Integer price;
    private Integer numRooms;
    private Integer numAvail;


}
