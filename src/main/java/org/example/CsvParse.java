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

    public List<StationDate> createObjectFromCsv(String path){
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
        List<StationDate> stationDates = new ArrayList<>();
        Pattern pattern = Pattern.compile("[\\w\\s]*\\d{2}.\\d{2}.\\d{4}");
        for (String line : lines) {
            String[] elements = line.split(",");
            /*отсортировываем строки не подходящие для создания объекта*/
            if (elements.length != 2 || !line.matches(".*\\.+.*")) {
                // System.out.println("Wrong line");
                continue;
            }
            try {
                stationDates.add(new StationDate(elements[0],
                        (new SimpleDateFormat(dateFormat).parse(elements[1]))));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return  stationDates;
    }

    static class StationDate {
        String stationName;
        Date date;

        public StationDate(String stationName, Date date) {
            this.stationName = stationName;
            this.date = date;
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
