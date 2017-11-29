package com.sabel.bitcoin;

import java.sql.SQLException;

public class Testklasse {

    public static void main(String[] args) throws SQLException {

        RateDB rdb = new RateDB();

        rdb.add(new Rate(1, 20.12, 21.11));
        rdb.add(new Rate(2, 33.33, 44.44));
        rdb.add(new Rate(3, 55.55, 66.66));
        rdb.add(new Rate(6, 6, 66));
        rdb.add(new Rate(5, 7, 77));
        rdb.add(new Rate(4, 8, 88));

        System.out.println(rdb.toString());

        System.out.println("--getLastRate2---------------------------------");
        System.out.println(rdb.getLastRate2());
        System.out.println("-----------------------------------------------\n\r");

        RateService rateService = new RateService();

        //rateService.save(new Rate(6, 6, 66));
        //rateService.save(new Rate(5, 7, 77));
        //rateService.save(new Rate(4, 8, 88));

        RateDB rateDB = rateService.readAllRates();

        System.out.println(rateDB.toString());
        System.out.println("-----------------------------------------------");

        System.out.println(rateService.readLastRate());


    }

}
