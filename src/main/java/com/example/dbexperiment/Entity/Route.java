package com.example.dbexperiment.Entity;

import lombok.Data;

import java.util.List;
@Data
public class Route {
    private String username;
    private List<Flight> flightList;
    private List<Bus> busList;
    private List<Hotel> hotelList;
    private String route_str;
    private List<Reservation> reservationList;
    private List<ResvTuple> resvTupleList;
}
