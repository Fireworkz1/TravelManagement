package com.example.dbexperiment.Mapper;

import com.example.dbexperiment.Entity.Hotel;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface HotelMapper {
    @Insert("insert into hotels(location,price,numRooms,numAvail)values(#{location},#{price},#{numRooms},#{numAvail})")
    void add(String location, Integer price, Integer numRooms, Integer numAvail);

    @Delete("delete from hotels where location=#{location}")
    void delete(String location);

    @Select("select * from hotels")
    List<Hotel> select();
    //当有人选座时，可坐位置-1
    @Select("select * from hotels where location=#{location}")
    Hotel selectById(String location);
    @Update("update hotels set numAvail=numAvail-1 where location=#{location}")
    void updateChoose(String location);
    @Update("update hotels set numAvail=numAvail+1 where location=#{location}")
    void updateCancel(String location);
}
