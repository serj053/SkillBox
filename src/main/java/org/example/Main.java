package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {

        FindFileInDirectory files = new FindFileInDirectory();
        List<String> listCsv = new ArrayList<>();
        /*возвращает список файлов из выбранной папки с определенным расширением*/
        files.find(new File("data"), listCsv, "csv");
        //listCsv.forEach(System.out::println);
        System.out.println("listCsv.get(1) " + listCsv.get(1));
        /*получаем файл из коллекции для разбора*/
        List<String> lines = Files.readAllLines(Paths.get(listCsv.get(1)));
        /*Готовим формат для даты*/
        String dateFormat = "dd.MM.yyyy";
        /*Готовим список для объектов*/
        List<CsvParse.StationDate> stationDates = new ArrayList<>();

        Pattern pattern = Pattern.compile("[\\w\\s]*\\d{2}.\\d{2}.\\d{4}");
        for (String line : lines) {
            String[] elements = line.split(",");
            if (elements.length != 2 && !line.contains("*.*")) {
                System.out.println("Wrong line");
                continue;
            }
            if(true) {
                System.out.println(elements[0] + "  " + elements[1]);
            }

        }

//
//        stationDates.add(new CsvParse.StationDate(elements[0],
//                (new SimpleDateFormat(dateFormat))
//                        .parse(elements[1])));
        /*Парсинг JSON файла**************************************************/
//        FindFileInDirectory files = new FindFileInDirectory();
//        List<String> listJs = new ArrayList<>();
///*возвращает список файлов из выбранной папки с определенным расширением*/
//       files.find(new File("data"), listJs, "json");
//      //listJs.forEach(System.out::println);
//
//
///*передаем путь к файлу для его парсинга в объект*/
//        JsParse  jsObj = new JsParse(listJs.get(0));
///*получаем список объектов полученных из Json файла*/
//    List<JsParse.StationDepth> listStationDepth = jsObj.getJsonObjects();
//    listStationDepth.forEach(System.out::println);
/*******************************************************************************/

    }

}


/*
*
*  FileReader read = new FileReader("data/2/4/depths-1.json");
       JSONParser parser = new JSONParser();
       JSONArray array = (JSONArray) parser.parse(read);
       for(Object obj: array){
           JSONObject jobj = (JSONObject) obj;
           String name = (String) jobj.get("station_name");
           String depth = (String) jobj.get("depth");
           System.out.println(depth + "  " + name);
       }

* */