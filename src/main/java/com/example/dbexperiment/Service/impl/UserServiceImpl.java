package com.example.dbexperiment.Service.impl;

import com.example.dbexperiment.Entity.*;
import com.example.dbexperiment.Mapper.*;
import com.example.dbexperiment.Service.UserService;
import com.example.dbexperiment.util.Enum.SvcTypeEnum;
import com.example.dbexperiment.util.Other.ResvTupleSorter;
import com.example.dbexperiment.util.Re.DateTimeExtractor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
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
    public boolean createUser(String username, String password) {
        if (username != null && password != null) {
            customerMapper.add(username, password);
            return true;
        } else
            return false;

    }

    @Override
    public boolean chooseFlight(String target, String username, Date time) {
        Flight f = flightMapper.selectById(target);
        if ((f != null) && (f.getNumAvail() != 0)) {
            flightMapper.updateChoose(f.getFlightNum());
            resvMapper.insert(username, SvcTypeEnum.flight.getCode(), f.getFlightNum(),time);
            return true;
        } else return false;

    }

    @Override
    public boolean chooseHotel(String target, String username, Date time, Date objTime) {
        Hotel h = hotelMapper.selectById(target);
        if ((h != null) && (h.getNumAvail() != 0)) {
            flightMapper.updateChoose(h.getLocation());
            resvMapper.insertHotel(username, SvcTypeEnum.hotel.getCode(), h.getLocation(),time,objTime);
            return true;
        } else return false;
    }

    @Override
    public boolean chooseBus(String target, String username, Date time) {
        Bus b = busMapper.selectById(target);
        if ((b != null) && (b.getNumAvail() != 0)) {
            busMapper.updateChoose(b.getLocation());
            resvMapper.insert(username, SvcTypeEnum.bus.getCode(), b.getLocation(),time);
            return true;
        } else return false;
    }

    @Override
    public boolean cancelFlight(String target, String username) {
        return false;
    }

    @Override
    public boolean cancelHotel(String target, String username) {
        return false;
    }

    @Override
    public boolean cancelBus(String target, String username) {
        return false;
    }

    @Override
    public boolean cancel(String target, String username) {
        Reservation r = resvMapper.selectDetail(username, target);
        if (r != null) {
            resvMapper.delete(r.getResvId());
            if (r.getResvType().equals(SvcTypeEnum.flight.getCode())) {
                flightMapper.updateCancel(r.getResvKey());
            } else if (r.getResvType().equals(SvcTypeEnum.bus.getCode())) {
                busMapper.updateCancel(r.getResvKey());
            } else if (r.getResvType().equals(SvcTypeEnum.hotel.getCode())) {
                hotelMapper.updateCancel(r.getResvKey());
            }
            return true;
        } else return false;
    }

    @Override
    public List<Flight> searchFlight() {return flightMapper.select();}

    @Override
    public List<Hotel> searchHotel() {
        return hotelMapper.select();
    }

    @Override
    public List<Bus> searchBus() {
        return busMapper.select();
    }

    @Override
    public Route travelRoute(String username) {
        Route route = new Route();
        List<Flight> flights=new ArrayList<>();
        List<Bus> buses=new ArrayList<>();
        List<Hotel> hotels=new ArrayList<>();
        StringBuilder msg = new StringBuilder();
        msg.append("您（");
        msg.append(username);
        msg.append("）当前的路线为：");
        List<Reservation> reservationList=resvMapper.selectByCustomer(username);

        int time=0;
        assert reservationList != null;
        int size= reservationList.size();
        boolean iscomplete=true;
        boolean hotelreserved=false;
        String s1 = null;
        String e1 = null;
        String e2 = null;
        String s2 = null;
        for (Reservation reservation : reservationList) {
            time++;
            /*
            判断飞机是否首尾相接
             */

            if (time == 1) {
                if (!reservation.getResvType().equals(SvcTypeEnum.flight.getCode())) {
                    iscomplete = false;
                } else {
                    Flight flight = flightMapper.selectById(reservation.getResvKey());

                    s1 = flight.getFromCity();

                    e1 = flight.getArivCity();
                }
            }


            if (time == size) {
                if (!reservation.getResvType().equals(SvcTypeEnum.flight.getCode())) {
                    iscomplete = false;
                } else {
                    Flight flight = flightMapper.selectById(reservation.getResvKey());
                    s2 = flight.getFromCity();
                    e2 = flight.getArivCity();
                }
            }

            if (!s1.equals(e2) || ! s2.equals(e1)){
                iscomplete=false;
            }
            /*
            判断是否有预定宾馆
             */
            if (reservation.getResvType().equals(SvcTypeEnum.hotel.getCode())){
                hotelreserved=true ;
            }


            /*
            为Route类对象赋值
             */
            if(reservation.getResvType().equals(SvcTypeEnum.flight.getCode()))
                flights.add(flightMapper.selectById(reservation.getResvKey()));
            else if (reservation.getResvType().equals(SvcTypeEnum.hotel.getCode()))
                hotels.add(hotelMapper.selectById(reservation.getResvKey()));
            else if (reservation.getResvType().equals(SvcTypeEnum.bus.getCode()))
                buses.add(busMapper.selectById(reservation.getResvKey()));
             /*
            为Route.msg对象赋值
             */
            if(reservation.getResvType().equals(SvcTypeEnum.flight.getCode()))
                msg.append("飞机：");
            else if (reservation.getResvType().equals(SvcTypeEnum.hotel.getCode()))
                msg.append("酒店：");
            else if (reservation.getResvType().equals(SvcTypeEnum.bus.getCode()))
                msg.append("公交：");
            msg.append(reservation.getResvKey());
//            msg.append("-");
        }
        msg.append("结束\n");
        if(iscomplete&&hotelreserved){
            msg.append("当前行程完整");
        }else{
            msg.append("当前行程不完整");
        }
        route.setRoute_str(msg.toString());
        route.setFlightList(flights);
        route.setBusList(buses);
        route.setHotelList(hotels);
        route.setResvTupleList(routeInfo(username,route));
        return route;
    }

    @Override
    public List<ResvTuple> routeInfo(String username, Route route) {
        List<ResvTuple> resvTupleList = new ArrayList<>();


        for(Flight flight :route.getFlightList()){
            ResvTuple tuple=new ResvTuple();
            tuple.setId(resvMapper.selectId(username,flight.getFlightNum()));
            tuple.setName(flight.getFlightNum());
            tuple.setType("飞机");
            tuple.setDate(flight.getTime());
            resvTupleList.add(tuple);

        }
        for(Bus bus :route.getBusList()){
            ResvTuple tuple=new ResvTuple();
            tuple.setId(resvMapper.selectId(username,bus.getLocation()));
            tuple.setName(bus.getLocation());
            tuple.setType("公交");
            tuple.setDate(bus.getTime());
            resvTupleList.add(tuple);
        }
        for(Hotel hotel :route.getHotelList()){
            ResvTuple tuple=new ResvTuple();
            tuple.setId(resvMapper.selectId(username,hotel.getLocation()));
            tuple.setName(hotel.getLocation());
            tuple.setType("酒店");

            List<Reservation> reservationList= resvMapper.selectByCustomer(username);
            for(Reservation reservation : reservationList)
                if(Objects.equals(reservation.getResvKey(), hotel.getLocation())){
                    tuple.setDate(reservation.getObjTime());
                    break;
                }

            resvTupleList.add(tuple);

        }


        ResvTupleSorter.bubbleSort(resvTupleList);
        for(ResvTuple resvTuple:resvTupleList){
            resvTuple.setDateString(DateTimeExtractor.dealString(resvTuple.getDate()));
        }
        return resvTupleList;
    }

    @Override
    public boolean addUser(String name,String pswd){
        if(customerMapper.selectId(name)==null){
            customerMapper.add(name,pswd);
            return true;
        }
        return false ;

    }
    @Override
    public boolean userLogin(String name,String pswd){
        if(customerMapper.select(name,pswd)==null){
            return false;
        }else{
            return true;
        }

    }
}
