package com.example.dbexperiment.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Reservation {
    private String custName;
    private Integer resvType;
    private String resvKey;//预定标识号；宾馆，公交车和飞机的名字id
    private Integer resvId;
    private Date resvTime;//预约时间
    private Date objTime;//目标时间
}
