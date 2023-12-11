package com.example.dbexperiment.Mapper;

import com.example.dbexperiment.Entity.Reservation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ResvMapper {

    @Select("select * from reservations where custName=#{custName} order by objTime")
    List<Reservation> selectByCustomer(String custName);
    @Select("select * from reservations where custName=#{custName} and resvKey=#{resvKey}")
    Reservation selectDetail(String custName,String resvKey);
    @Select("select resvId from reservations where custName=#{custName} and resvKey=#{resvKey}")
    Integer selectId(String custName,String resvKey);
    @Delete("delete from reservations where resvId=#{resvId} ")
    void delete(Integer resvId);
    @Delete("delete from reservations where custName=#{custName} ")
    void deleteByUser(String custName);
    @Insert("insert into reservations(custName,resvType,resvKey,resvTime) values(#{custName},#{resvType},#{resvKey},#{resvTime})")
    void insert(String custName, Integer resvType, String resvKey, Date resvTime);
    @Insert("insert into reservations(custName,resvType,resvKey,resvTime,objTime) values(#{custName},#{resvType},#{resvKey},#{resvTime},#{objTime})")
    void insertHotel(String custName, Integer resvType, String resvKey, Date resvTime,Date objTime);
}
