package org.example;

import org.json.simple.parser.ParseException;

import java.io.File;;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        /*Выводим список путей файлов с определнным расширением */
        System.out.println("/*Выводим список файлов с определнным расширением */");
        FindFileInDirectory filesJs = new FindFileInDirectory();
        List<String> listJsnPath = new ArrayList<>();
        List<String> listCsv = new ArrayList<>();
        /*возвращает список путей файлов из выбранной папки с определенным расширением*/
        filesJs.find(new File("data"), listJsnPath, "json");
        listJsnPath.forEach(System.out::println);
        System.out.println();
        filesJs.find(new File("data"), listCsv, "csv");
        listCsv.forEach(System.out::println);
        System.out.println("\n");

        /*Парсинг JSON файла выводим объекты полученные из JSON  файла *****/
        System.out.println("/*Парсинг JSON файла выводим объекты полученные из JSON  файла *****/");
        //создаем объект для парсинга
        JsParse jsObj = new JsParse();
        ArrayList<JsParse.StationDepth> listJs = new ArrayList<>();
        //передаем в метод путь к JSON файлу и массив для  новых объектов и
        // получаем список объектов полученных из Json файла
        jsObj.getJsonObjects(listJs, listJsnPath.get(0));
        jsObj.getJsonObjects(listJs, listJsnPath.get(1));
        List<JsParse.StationDepth> listStationDepth = jsObj.getJsonObjects(listJs, listJsnPath.get(2));
        for (int i = 0; i < listStationDepth.size(); i++) {
            System.out.println(i + "   station " + listStationDepth.get(i).getStation()
                    + ",  depth " + listStationDepth.get(i).getDepth());
        }

        System.out.println("\n");

        /*Парсинг CSV фала - выводим объекты полученные из CSV файла*/
        System.out.println("/*Парсинг CSV фала - выводим объекты полученные из CSV файла*/");
        System.out.println("listCsv.get(1) " + listCsv.get(1));
        //получаем список с объектами
        ArrayList<CsvParse.StationDate> stationDate = new ArrayList<>();
        List<CsvParse.StationDate> list;
        CsvParse csvParse = new CsvParse();
        csvParse.createObjectFromCsv(stationDate, listCsv.get(0));
        csvParse.createObjectFromCsv(stationDate, listCsv.get(1));
        list = csvParse.createObjectFromCsv(stationDate, listCsv.get(2));
        AtomicInteger nn = new AtomicInteger();
        list.forEach(o -> System.out.println(nn.getAndIncrement() + "  " +
                o.getStationName()  + "  " + o.getDate()));
        System.out.println("\n");

        /*Получаем JSON файлы из объектов находящихся в коллекциях
        * ArrayList<JsParse.StationDepth> listStationDepth
        * List<CsvParse.StationDate> list */
//        System.out.println("/*Получаем JSON файлы из объектов*/");
//        String result = null;
//        for(JsParse.StationDepth sd : listStationDepth){
//            ObjectMapper objectMapper = new ObjectMapper();
//            result = objectMapper.writeValueAsString(sd);
//            System.out.println(result);
//        }


    }

}
