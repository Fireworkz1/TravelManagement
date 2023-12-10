package com.example.dbexperiment.Service.impl;

import com.example.dbexperiment.Entity.Bus;
import com.example.dbexperiment.Entity.Customer;
import com.example.dbexperiment.Entity.Flight;
import com.example.dbexperiment.Entity.Hotel;
import com.example.dbexperiment.Mapper.*;
import com.example.dbexperiment.Service.RootService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        flightMapper.add(flight.getFlightNum(),flight.getPrice(),flight.getNumSeats(),flight.getNumSeats(), flight.getFromCity(), flight.getArivCity(),flight.getTime());
    }

    @Override
    public void addHotel(Hotel hotel) {
        hotelMapper.add(hotel.getLocation(),hotel.getPrice(),hotel.getNumRooms(),hotel.getNumRooms());
    }

    @Override
    public void addBus(Bus bus) {
        busMapper.add(bus.getLocation(),bus.getPrice(),bus.getNumBus(),bus.getNumBus(),bus.getTime());
    }


    @Override
    public void deleteFlight(String id) {
        if(flightMapper.selectById(id)!=null){
            flightMapper.delete(id);
        }

    }

    @Override
    public List<Customer> selectAllUser() {
        return customerMapper.selectAll();
    }

    @Override
    public void delUser(String name) {
        customerMapper.delUser(name);
        resvMapper.deleteByUser(name);
    }

    @Override
    public void selectResv() {

    }

    @Override
    public void deleteHotel(String id) {
        if(hotelMapper.selectById(id)!=null){
            hotelMapper.delete(id);
        }
    }

    @Override
    public void deleteBus(String id) {
        if(busMapper.selectById(id)!=null){
            busMapper.delete(id);
        }
    }

    @Override
    public String travelRoute(String userid) {
        return null;
    }

    @Override
    public void defaultPswd(String name) {
        customerMapper.changePswd(name);
    }
}
