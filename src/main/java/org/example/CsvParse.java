package org.example;

import java.util.Date;


public class CsvParse {




    static class StationDate{
        String stationName;
        Date date;

    public StationDate(String stationName, Date date) {
        this.stationName = stationName;
        this.date = date;
    }

        @Override
        public String toString () {
        return "CsvParse{" +
                "stationName='" + stationName + '\'' +
                ", date=" + date +
                '}';
    }
    }

}
