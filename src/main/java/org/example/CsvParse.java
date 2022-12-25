package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class CsvParse {

    public List<StationDate> createObjectFromCsv(ArrayList<StationDate> stationDates, String path) {
        /*получаем файл из коллекции для разбора*/
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*Готовим формат для даты*/
        String dateFormat = "dd.MM.yyyy";
        /*Готовим список для объектов*/
        // Pattern pattern = Pattern.compile("[\\w\\s]*\\d{2}.\\d{2}.\\d{4}");
        for (String line : lines) {
            String[] elements = line.split(",");
            /*отсортировываем строки не подходящие для создания объекта*/
            if (elements.length != 2 || !line.matches(".*\\.+.*")) {
                // System.out.println("Wrong line");
                continue;
            }
            /*игнорируем повторяющиеся элементы*/
            if (stationDates.size() == 0) {
                try {
                    stationDates.add(new StationDate(elements[0],
                            (new SimpleDateFormat(dateFormat).parse(elements[1]))));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            int flag = 0;
            for (int i = 0; i < stationDates.size(); i++) {
                if (!stationDates.get(i).stationName.equals(elements[0])) {
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
