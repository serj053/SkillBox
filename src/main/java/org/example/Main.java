package org.example;

import org.json.JSONObject;
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

        GetHTML html = new GetHTML();
        ArrayList<String> getLine = new ArrayList<>();
        ArrayList<String> getStations = new ArrayList<>();
        getLine =  html. new GetLines().getMetroLine();
        getStations = html. new GetStations().getMetroStation();
        for(int i = 0 ; i < getStations.size(); i++){
                for(int j = 1 ; j < getStations.size()  ; j++){
                    String str1 = getStations.get(i).substring(11);
                    String str11 = getStations.get(i).substring(8,9);
                    String str2 = getStations.get(j).substring(11);
                    String str22 = getStations.get(j).substring(8,9);
                     //String[] str2 = getStations.get(j).split("[\\s]");
                    //System.out.println("str1 " + str1[4]);
                    if(str1.equals(str2) && !str11.equals(str22)){
                        System.out.println("Interconnection  "
                                + str1 + " " + str11 +
                                " " + str2 + " " + str22);
                    }

                }
        }

        /*получаем со страницы данные - линии Московского метро и ее номер
        *                             - станции московского метро и  номер линии*/
        System.out.println("*получаем со страницы данные\n " +
                "- линии Московского метро и ее номер" );
        GetHTML getHTML = new GetHTML();
        getHTML. new GetLines().getMetroLine().forEach(System.out::println);
        System.out.println();
        System.out.println("-название станций и номер линий");
        System.out.println();
        getHTML. new GetStations().getMetroStation().forEach(System.out::println);
        System.out.println();

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
        * ArrayList<JsParse.StationDepth> listStationDepth из JSON файлов
        * List<CsvParse.StationDate> list   из CSV файлов*/
       System.out.println("/*Получаем JSON файлы из объектов*/");
        JSONObject obj = new JSONObject();
        obj.put("first","second");
        System.out.println(obj);


    }

}
