package com.example.dbexperiment.Mapper;

import com.example.dbexperiment.Entity.Flight;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlightMapper {
    @Insert("insert into flights(flightNum,price,numSeats,numAvail,fromCity,arivCity)values(#{flightNum},#{price},#{numSeats},#{numAvail},#{fromCity},#{arivCity})")
    void add(String flightNum, Integer price, Integer numSeats, Integer numAvail, String fromCity, String arivCity);

    @Delete("delete from flights where flightNum=#{flightNum}")
    void delete(String flghtNum);

    @Select("select * from flights")
    List<Flight> select();
    //当有人选座时，可坐位置-1
    @Select("select * from flights where flightNum=#{flightNum}")
    Flight selectById(String flightNum);
    @Update("update flights set numAvail=numAvail-1 where flightNum=#{flightNum}")
    void updateChoose(String flightNum);
    @Update("update flights set numAvail=numAvail+1 where flightNum=#{flightNum}")
    void updateCancel(String flightNum);
}
