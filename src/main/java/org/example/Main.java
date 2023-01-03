package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException, ParseException {




        /*получаем со страницы данные - линии Московского метро и ее номер
         *                             - станции московского метро и  номер линии*/
        System.out.println("*получаем со страницы данные\n " +
                "- линии Московского метро и ее номер");
        GetHTML getHTML = new GetHTML();
        HashMap<String, String> metroLines ;
        metroLines = getHTML.new GetLines().getMetroLine();
        metroLines.entrySet().stream()
                .map(m -> {
                    System.out.println(m.getKey() + "  " + m.getValue());
                    return 0;
                }).toList();

        System.out.println();
        System.out.println("-название станций и номер линий");
        System.out.println();
        ArrayList<String> metroStations = getHTML.new GetStations().getMetroStation();
        getHTML.new GetStations().getMetroStation().forEach(System.out::println);
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
                o.getStationName() + "  " + o.getDate()));
        System.out.println("\n");

        /*Получаем JSON файлы из объектов находящихся в коллекциях
         * ArrayList<JsParse.StationDepth> listStationDepth из JSON файлов
         * List<CsvParse.StationDate> list   из CSV файлов*/
        System.out.println("/*Получаем JSON файлы из объектов*/");
        JSONObject obj = new JSONObject();
        obj.put("first", "second");
        System.out.println(obj);
        /*
        *
        * GetHTML getHTML = new GetHTML();
        ArrayList<String> metroLines = getHTML.new GetLines().getMetroLine();
        getHTML.new GetLines().getMetroLine().forEach(System.out::println);
        System.out.println();
        System.out.println("-название станций и номер линий");
        System.out.println();
        ArrayList<String> metroStations = getHTML.new GetStations().getMetroStation();
        getHTML.new GetStations().getMetroStation().forEach(System.out::println);
        System.out.println();
        *
        * getLine - линии метро список
        * getStations - станции метро список
        * */


        GetHTML html = new GetHTML();
        Map<String, String> getLine = new HashMap<>();
        ArrayList<String> getStations = new ArrayList<>();
        getLine = html.new GetLines().getMetroLine();
        getStations = html.new GetStations().getMetroStation();
        for (int i = 0; i < getStations.size(); i++) {//берем текущую станцию
            for (int j = 1; j < getStations.size(); j++) {//берем следующую станцию
                String str1 = getStations.get(i).substring(11);//подстрока название первой станции
                String str11 = getStations.get(i).substring(8, 9);//подстрока номер первой линии
                String str2 = getStations.get(j).substring(11);//подстрока название второй станции
                String str22 = getStations.get(j).substring(8, 9);//подстрока номер второй линии
                if (str1.equals(str2) && !str11.equals(str22)//имена ровны номера не ровны
                        && getLine.get(str11) != null && getLine.get(str22) != null
                ) {
                    System.out.println(
                            "станция " +
                                    str1 +
                                    ", переход между ветками  "
                                    + " " + getLine.get(str11) + " и "
                                    + " " + getLine.get(str22) + "  на станцию " +
                                    str2
                            //+ (metroLines.toString())
                    );
                }
            }
        }
        System.out.println("****************************************************");

        JSONObject result  = new JSONObject();
        result = JsonFrom.listLinesStations(getStations);

        System.out.println(result);
        JSONArray arr = JsonFrom.listConnections(getStations);
        System.out.println(arr);
        System.out.println("======================================================");
        JSONArray arr1 = JsonFrom.metroLines(metroLines);
        System.out.println(arr1);
        System.out.println("======================================================");
        JSONObject object = JsonFrom.listStationsAndLines()
    }

}
