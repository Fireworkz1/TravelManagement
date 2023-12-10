package com.example.dbexperiment.Service;

import com.example.dbexperiment.Entity.*;

import java.util.Date;
import java.util.List;

public interface UserService {
        boolean createUser(String username,String password);
        boolean chooseFlight(String target,String username, Date time);
        boolean chooseHotel(String target,String username, Date time);
        boolean chooseBus(String target,String username, Date time);
        boolean cancelFlight(String target,String username);
        boolean cancelHotel(String target,String username);
        boolean cancelBus(String target,String username);
        boolean cancel(String target,String username);



        boolean addUser(String name, String pswd);
        boolean userLogin(String name,String pswd);
        List<Flight> searchFlight();
        List<Hotel> searchHotel();
        List<Bus> searchBus();

        Route travelRoute(String username);
        List<ResvTuple> routeInfo(String username,Route route);
}
