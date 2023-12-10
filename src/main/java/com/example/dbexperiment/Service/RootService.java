package com.example.dbexperiment.Service;

import com.example.dbexperiment.Entity.Bus;
import com.example.dbexperiment.Entity.Customer;
import com.example.dbexperiment.Entity.Flight;
import com.example.dbexperiment.Entity.Hotel;

import java.util.List;

public interface RootService {



    void addFlight(Flight flight);


    void addHotel(Hotel hotel);

    void addBus(Bus bus);

    void deleteFlight(String id);

    List<Customer> selectAllUser();
    void delUser(String name);
    void selectResv();

    void deleteHotel(String id);

    void deleteBus(String id);
    String travelRoute(String userid);

    void defaultPswd(String name);
}

