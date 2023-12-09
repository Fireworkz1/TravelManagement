package com.example.dbexperiment.Mapper;

import com.example.dbexperiment.Entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
    @Insert("insert into customers( custName, custPassword) values(#{custName},#{custPassword})")
    void add(String custName,String custPassword);

    @Select("select * from customers where custPassword = #{custPassword} and custName = #{custName} ")
    Customer select(@Param("custName") String custName,@Param("custPassword") String custPassword);
    @Select("select * from customers where custName=#{custName}")
    Customer selectId(String custName);

}
