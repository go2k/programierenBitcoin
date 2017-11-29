package com.sabel.bitcoin;


import java.sql.SQLException;
import java.util.Date;

public class Testklasse {

    public static void main(String[] args) {



            RateDB rdb = new RateDB();

            rdb.add(new Rate(new Date().getTime() / 1000, 20.12, 21.11));
            rdb.add(new Rate(new Date().getTime() / 1000, 33.33, 44.44));
            rdb.add(new Rate(new Date().getTime() / 1000, 55.55, 66.66));
            rdb.add(new Rate(new Date().getTime() / 1000, 6, 66));
            rdb.add(new Rate(new Date().getTime() / 1000, 7, 77));
            rdb.add(new Rate(new Date().getTime() / 1000, 8, 88));

            System.out.println(rdb.toString());

            System.out.println("--getLastRate2---------------------------------");
            System.out.println(rdb.getLastRate2());
            System.out.println("--getLastRate---------------------------------");
            System.out.println(rdb.getLastRate());
            System.out.println("-----------------------------------------------\n\r");

            RateService rateService = null;

        try {
            rateService = new RateService();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //rateService.save(new Rate(6, 6, 66));
            //rateService.save(new Rate(5, 7, 77));
            //rateService.save(new Rate(4, 8, 88));

        RateDB rateDB = null;
        try {
            rateDB = rateService.readAllRates();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(rateDB.toString());
            System.out.println("-----------------------------------------------");

        try {
            System.out.println(rateService.readLastRate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-String formatieren s = String n = newLine ----");
            System.out.printf("%s%n%s%n", "Hallo", "Welt");

        try {
            rateService.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
