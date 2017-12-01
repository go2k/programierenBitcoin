package com.sabel.bitcoin;

import java.sql.*;

public class RateService {

    Connection connection;
    PreparedStatement pStatInsert;
    PreparedStatement pStatSelect;
    PreparedStatement pStatSelectAll;

    public RateService() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:ratedb.sqlite");
        this.pStatInsert = connection.prepareStatement("insert into rates (ts, rateEUR, rateUSD) VALUES (?,?,?)");
        this.pStatSelect = connection.prepareStatement("select ts, rateEUR, rateUSD from rates order by ts desc limit 1");
        this.pStatSelectAll = connection.prepareStatement("select ts, rateEUR, rateUSD from rates order by ts asc");
    }

    public void close() throws SQLException {
        if (pStatSelect != null) {
            this.pStatInsert.close();
        }
        if (pStatSelect != null) {
            this.pStatSelect.close();
        }
        if (pStatSelect != null) {
            this.connection.close();
        }
        this.connection = null;

    }

    public void save(Rate rate) throws SQLException {
        pStatInsert.setLong(1, rate.getTimestamp());
        pStatInsert.setDouble(2, rate.getRateEUR());
        pStatInsert.setDouble(3, rate.getRateUSD());
        pStatInsert.execute();
    }

    public RateDB readAllRates() throws SQLException {
        RateDB rdb = new RateDB();
        ResultSet rs=pStatSelectAll.executeQuery();

        while (rs.next()) {
            rdb.add( new Rate(rs.getLong("ts"), rs.getDouble("rateEUR"), rs.getDouble("rateUSD")));
        }
        return rdb;
    }

    public Rate readLastRate() throws SQLException {
        Rate r = new Rate();
        ResultSet rs = pStatSelect.executeQuery();

        r.setTimestamp(rs.getLong("ts"));
        r.setRateEUR(rs.getDouble("rateEUR"));
        r.setRateUSD(rs.getDouble("rateUSD"));
        return r;
    }
}
