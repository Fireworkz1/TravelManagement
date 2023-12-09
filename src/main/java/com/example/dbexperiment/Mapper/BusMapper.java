package com.example.dbexperiment.Mapper;

import com.example.dbexperiment.Entity.Bus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusMapper {
    @Insert("insert into bus(location,price,numBus,numAvail)values(#{location},#{price},#{numBus},#{numAvail})")
    void add(String location, Integer price, Integer numBus, Integer numAvail);

    @Delete("delete from bus where location=#{location}")
    void delete(String location);

    @Select("select * from bus")
    List<Bus> select();
    @Select("select * from bus where location=#{location}")
    Bus selectById(String location);
    //当有人选座时，可坐位置-1
    @Update("update bus set numAvail=numAvail-1 where location=#{location}")
    void updateChoose(String location);
    @Update("update bus set numAvail=numAvail+1 where location=#{location}")
    void updateCancel(String location);

}
