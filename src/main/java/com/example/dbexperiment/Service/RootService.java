package com.example.dbexperiment.Service;

import com.example.dbexperiment.Entity.Flight;

public interface RootService {



    void addFlight(Flight flight);

    void addHotel(String target);
    void addBus(String target);
    void deleteFlight(String id);

    void deleteHotel(Integer id);
    void deleteBus(Integer id);
    String travelRoute(Integer userid);
}
