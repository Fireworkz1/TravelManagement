create database reservesys;
use reservesys;
create table flights(
                        flightNum varchar(255) primary key,
                        price int,
                        numSeats int,
                        numAvail int,
                        fromCity varchar(255),
                        arivCity varchar(255)
);

create table hotels(
                       location varchar(255) primary key,
                       price int,
                       numRooms int,
                       numAvail int
);

create table bus(
                    location varchar(255) primary key,
                    price int,
                    numBus int,
                    numAvail int
);
create table customers(
                          custID int primary key,
                          custName varchar(255)

);
CREATE INDEX idx_custName ON customers(custName);

create table reservations(
                             custName varchar(255),
                             foreign key (custName) references customers(custName),
                             resvType int,
                             resvKey varchar(255)
);