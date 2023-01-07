package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvParse {

    public ArrayList<StationDate> stationLaunchDate(ArrayList<StationDate> stationDates, String path) {
        /*получаем файл из коллекции для разбора*/
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*Готовим формат для даты*/
        String dateFormat = "dd.MM.yyyy";
        /*Готовим список для объектов*/
        for (String line : lines) {
            String[] elements = line.split(",");
            /*отсортировываем строки не подходящие для создания объекта*/
            if (elements.length != 2 || !line.matches(".*\\.+.*")) {
                continue;
            }
            /*игнорируем  элемент*/
            if (stationDates.size() == 0) {//первое добавление
                try {
                    stationDates.add(new StationDate(elements[0],
                            (new SimpleDateFormat("dd.MM.yyyy").parse(elements[1]))));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            int flag = 0;
            for (StationDate stationDate : stationDates) {
                if (!stationDate.stationName.equals(elements[0])) {
                    continue;
                }
                flag++;
            }
            if (flag == 0) {
                try {
                    stationDates.add(new StationDate(elements[0],
                            (new SimpleDateFormat(dateFormat).parse(elements[1]))));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return stationDates;
    }

    static class StationDate {
        private final String stationName;
        private final Date date;

        public StationDate(String stationName, Date date) {
            this.stationName = stationName;
            this.date = date;
        }

        public String getStationName() {
            return stationName;
        }

        public Date getDate() {
            return date;
        }

        @Override
        public String toString() {
            return "CsvParse{" +
                    "stationName='" + stationName + '\'' +
                    ", date=" + date +
                    '}';
        }

    }

}
