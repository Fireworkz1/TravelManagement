package com.example.dbexperiment.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class ResvTuple {
    private Integer id;
    private String name;
    private String type;
    private Date date;
    private String dateString;
}
