package com.sabel.bitcoin;

import java.util.*;

public class RateDB {

    private List<Rate> list;

    public RateDB() {
        list = new ArrayList<>();
    }

    public int size() {

        return list.size();

    }

    public boolean add(Rate rate) {
        return list.add(rate);
    }

    public boolean add(long timestamp, double rateEUR, double rateUSD) {
        return list.add(new Rate(timestamp, rateEUR, rateUSD));
    }

    public Rate getLastRate2() {
        Collections.sort(list, new Comparator<Rate>() {
            @Override
            public int compare(Rate o1, Rate o2) {
                return (int) (o1.getTimestamp() - o2.getTimestamp());
            }
        });
        return list.get(list.size() - 1);


    }

    public Rate getLastRate() {
        Rate returnRate = null;
        for (Rate rate : list) {
            if (returnRate != null) {
                if (rate.getTimestamp() > returnRate.getTimestamp()) {
                    returnRate = rate;
                }
            } else {
                returnRate = rate;
            }
        }
        return returnRate;
    }

    public Rate get(int index) {
        if (index >= 0 & index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    public List<Rate> get(long beginTimestamp, long endTimestamp) {

        List<Rate> retlist = new ArrayList<>();
        for (Rate rate : list) {
            if (rate.getTimestamp() >= beginTimestamp && rate.getTimestamp() <= endTimestamp) {
                retlist.add(rate);
            }
        }
        return retlist;
    }


    public Rate remove(long timestamp) {
        Iterator<Rate> iterator = list.iterator();
        while (iterator.hasNext()) {
            Rate nextRate = iterator.next();
            if (nextRate.getTimestamp() == timestamp) {
                iterator.remove();
                return nextRate;
            }
        }
        return null;
    }

    public Rate removeMeine(long timestamp) {
        Rate r = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTimestamp() == timestamp) {
                r = list.get(i);
                list.remove(i);
                break;
            }
        }
        return r;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("Zeitstempel RateinEuro");
        for (Rate rate : list) {
            stringBuilder.append(rate.getTimestamp() + " " + rate.getRateEUR() + String.format("%n"));
        }
        return stringBuilder.toString();


    }

//    @Override
//    public String toString() {
//        String s = "";
//        for (Rate rate : list) {
//            s += rate.getTimestamp() + " " + rate.getRateEUR() + "\r\n";
//        }
//        return s;
//
//    }
}

