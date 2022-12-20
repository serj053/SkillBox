package org.example;

import org.json.simple.parser.ParseException;

import java.io.File;;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;



public class Main {

    public static void main(String[] args) throws IOException, ParseException {

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
        List<String> listJsn = new ArrayList<>();
        List<String> listCsv = new ArrayList<>();
        /*возвращает список путей файлов из выбранной папки с определенным расширением*/
        filesJs.find(new File("data"), listJsn, "json");
        listJsn.forEach(System.out::println);
        System.out.println();
        filesJs.find(new File("data"), listCsv, "csv");
        listCsv.forEach(System.out::println);
        System.out.println("\n");

        /*Парсинг JSON файла выводим объекты полученные из JSON  файла *****/
        System.out.println("/*Парсинг JSON файла выводим объекты полученные из JSON  файла *****/");
        //создаем объект для парсинга
        JsParse jsObj = new JsParse();
        List<JsParse.StationDepth> listJs = new ArrayList<>();
        //передаем в метод путь к JSON файлу и массив для  новых объектов и
        // получаем список объектов полученных из Json файла
 // System.out.println(listJsn.get(0));
        jsObj.getJsonObjects(listJs,listJsn.get(0));
        jsObj.getJsonObjects(listJs,listJsn.get(1));
        List<JsParse.StationDepth> listStationDepth = jsObj.getJsonObjects(listJs,listJsn.get(2));
        System.out.println("size " + listJs.size());
        AtomicInteger n = new AtomicInteger();
        listStationDepth.forEach(o-> System.out.println(n.getAndIncrement() +"  " + o));
        System.out.println("\n");

        /*Парсинг CSV фала - выводим объекты полученные из CSV файла*/
        System.out.println("/*Парсинг CSV фала - выводим объекты полученные из CSV файла*/");
        System.out.println("listCsv.get(1) " + listCsv.get(1));
        //получаем список с объектами
        List<CsvParse.StationDate> list;
        list = new CsvParse().createObjectFromCsv(listCsv.get(0));
        AtomicInteger nn = new AtomicInteger();
        list.forEach(o-> System.out.println(nn.getAndIncrement() + "  " + o));
        System.out.println("\n");

/*Получаем JSON файлы из объектов*/
//        System.out.println("/*Получаем JSON файлы из объектов*/");
//        String result = null;
//        for(JsParse.StationDepth sd : listStationDepth){
//            ObjectMapper objectMapper = new ObjectMapper();
//            result = objectMapper.writeValueAsString(sd);
//            System.out.println(result);
//        }



    }

}
