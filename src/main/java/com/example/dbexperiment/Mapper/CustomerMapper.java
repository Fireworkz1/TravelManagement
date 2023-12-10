package com.example.dbexperiment.Mapper;

import com.example.dbexperiment.Entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Insert("insert into customers( custName, custPassword) values(#{custName},#{custPassword})")
    void add(String custName,String custPassword);

    @Select("select * from customers where custPassword = #{custPassword} and custName = #{custName} ")
    Customer select(@Param("custName") String custName,@Param("custPassword") String custPassword);
    @Select("select * from customers where custName=#{custName}")
    Customer selectId(String custName);

    @Select("select * from customers")
    List<Customer> selectAll();

    @Delete("delete from customers where custName=#{custName}")
    void delUser(String custName);

    @Update("update customers set custPassword='123456' where custName=#{custName}")
    void changePswd(String custName);

}
