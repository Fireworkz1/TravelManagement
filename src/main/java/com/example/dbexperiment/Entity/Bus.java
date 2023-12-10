package com.example.dbexperiment.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Bus {
    private String location;
    private Integer price;
    private Integer numBus;
    private Integer numAvail;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;


}
