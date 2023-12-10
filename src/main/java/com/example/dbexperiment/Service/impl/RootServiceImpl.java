package com.example.dbexperiment.Service.impl;

import com.example.dbexperiment.Entity.Flight;
import com.example.dbexperiment.Mapper.*;
import com.example.dbexperiment.Service.RootService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RootServiceImpl implements RootService {
    @Resource
    BusMapper busMapper;
    @Resource
    FlightMapper flightMapper;
    @Resource
    HotelMapper hotelMapper;
    @Resource
    ResvMapper resvMapper;
    @Resource
    CustomerMapper customerMapper;


    @Override
    public void addFlight(Flight flight) {
        flightMapper.add(flight.getFlightNum(),flight.getPrice(),flight.getNumSeats(),flight.getNumAvail(), flight.getFromCity(), flight.getArivCity(),flight.getTime());
    }

    @Override
    public void addHotel(String target) {

    }

    @Override
    public void addBus(String target) {

    }


    @Override
    public void deleteFlight(String id) {
            flightMapper.delete(id);
    }

    @Override
    public void deleteHotel(Integer id) {

    }

    @Override
    public void deleteBus(Integer id) {

    }

    @Override
    public String travelRoute(Integer userid) {
        return null;
    }
}
