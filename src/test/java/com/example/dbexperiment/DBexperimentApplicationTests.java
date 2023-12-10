package com.example.dbexperiment;

import com.example.dbexperiment.util.Re.DateTimeExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class DBexperimentApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(DateTimeExtractor.dealString(new Date()));
    }

}
