package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {


        System.out.println("/*получаем со страницы данные - номер линии Московского метро и имя линии метро*/");
        GetHTML getHTML = new GetHTML();
        HashMap<String, String> lineNumberLineName;
        lineNumberLineName = getHTML.new GetLines().linesNumbersLinesNames();
        lineNumberLineName.entrySet().stream()
                .map(m -> {
                    System.out.println(m.getKey() + "  " + m.getValue());
                    return 0;
                });

        System.out.println();
        System.out.println("-  номера линий и  название станций------------------------------------");
        System.out.println();
        ArrayList<String> lineNumberStationName = getHTML.new GetStations().lineNumbersStationsNamesArrList();
        int nnn = 0;
        for (String str : lineNumberStationName) {
            nnn++;
            System.out.println(nnn + " " + str);
        }
        System.out.println();

        /*Выводим список путей файлов с определнным расширением */
        System.out.println("/*Выводим список файлов с определнным расширением */");
        /*рекурсино проходим по папкам*/
        FindFileInDirectory filesJs = new FindFileInDirectory();

        List<String> listJsnPath = new ArrayList<>();
        List<String> listCsvPath = new ArrayList<>();
        /*возвращает список путей файлов из выбранной папки с  расширением JSON*/
        filesJs.find(new File("data"), listJsnPath, "json");
        listJsnPath.forEach(System.out::println);
        System.out.println();
        /*возвращает список путей файлов из выбранной папки с  расширением CSV*/
        filesJs.find(new File("data"), listCsvPath, "csv");
        listCsvPath.forEach(System.out::println);
        System.out.println("\n");

        /*Обработка CSV файлов */
        System.out.println("выводим значение глубины станции из CSV файла");
        //создаем объект для парсинга
        stationsDepth stationsDepth = new stationsDepth();
        //создаем коллекцию для данных
        HashMap<String, String> listCollectStationsDepths = new HashMap<>();
        //передаем в метод путь к JSON файлу и массив для  новых объектов и
        // получаем список объектов полученных из Json файла
        stationsDepth.getDepth(listCollectStationsDepths, listJsnPath.get(0));
        stationsDepth.getDepth(listCollectStationsDepths, listJsnPath.get(1));
        HashMap<String, String> listStationDepth = stationsDepth.getDepth(listCollectStationsDepths, listJsnPath.get(2));
        System.out.println("++++++++++++++ " + listStationDepth.keySet().size() + " +++++++++++");
        for (String str : listStationDepth.keySet()) {
            System.out.println(str + " " + listStationDepth.get(str));
        }
        System.out.println("\n");

        System.out.println("/*Парсинг CSV фала - выводим даты постройки станций полученные из CSV файла*/");
        System.out.println("listCsv.get(1) " + listCsvPath.get(1));
        //получаем список с объектами
        ArrayList<CsvParse.StationDate> listCollectStationDate = new ArrayList<>();
        ArrayList<CsvParse.StationDate> listDate;
        CsvParse csvParse = new CsvParse();
        csvParse.stationLaunchDate(listCollectStationDate, listCsvPath.get(0));
        csvParse.stationLaunchDate(listCollectStationDate, listCsvPath.get(1));
        listDate = csvParse.stationLaunchDate(listCollectStationDate, listCsvPath.get(2));
        /*форматируем дату*/
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd.MM.yyyy");
        AtomicInteger nn = new AtomicInteger();
        listDate.forEach(o -> System.out.println(nn.getAndIncrement() + "  " +
                o.getStationName() + "  " + simpleFormat.format(o.getDate())));
        System.out.println("\n");

        /*Получаем JSON файлы из объектов находящихся в коллекциях
         * ArrayList<JsParse.StationDepth> listStationDepth из JSON файлов
         * List<CsvParse.StationDate> list   из CSV файлов*/
        System.out.println("/*Получаем станции где есть переходы между линиями*/");
        GetHTML html = new GetHTML();
        /*ключ-имя станции, значение - номер линии */
        HashMap<String, String> nameStationNumberLine = new HashMap<>();
        /*имя станции -> переход есть? (true, false)*/
        HashMap<String, Boolean> hasInterconnection = new HashMap<>();
        //ArrayList<String> stationsNamesLines = new ArrayList<>();
        HashMap<String, String> linesNumbersLinesNames = html.new GetLines().linesNumbersLinesNames();
        //stationsNamesLines = html.new GetStations().stationsNamesLinenumbers();
        int n = 0;
        boolean hasInterconnect = false;
        for (int i = 0; i < lineNumberStationName.size(); i++) {//берем текущую станцию
            for (int j = 1; j < lineNumberStationName.size(); j++) {//берем следующую станцию
                String str1 = lineNumberStationName.get(i).substring(11);//подстрока название первой станции
                String str11 = lineNumberStationName.get(i).substring(8, 10);//подстрока номер первой линии
                String str2 = lineNumberStationName.get(j).substring(11);//подстрока название второй станции
                String str22 = lineNumberStationName.get(j).substring(8, 10);//подстрока номер второй линии
                nameStationNumberLine.put(str1.trim(), str11.trim());

                if (str1.equals(str2) && !str11.equals(str22)//имена ровны номера не ровны
                        && linesNumbersLinesNames.get(str11) != null && linesNumbersLinesNames.get(str22) != null
                ) {
                    n++;
                    System.out.println(
                            n + " " +
                                    "станция " +
                                    str1 +
                                    ", переход между ветками  "
                                    + " " + linesNumbersLinesNames.get(str11) + " и "
                                    + " " + linesNumbersLinesNames.get(str22) + "  на станцию " +
                                    str2
                    );
                    hasInterconnect = true;
                }
                hasInterconnection.put(str1.trim(), hasInterconnect);
            }
        }
        System.out.println("\nlistLinesWithStations  ****************************************************");

        JSONObject result = new JSONObject();
        result = JsonMain.listLinesWithStations(lineNumberStationName);
        System.out.println(result);

        System.out.println("\ntransferStations  ****************************************************");
        JSONArray arr = JsonMain.transferStations(lineNumberStationName);
        System.out.println(arr);

        System.out.println("\nmetroLines  ======================================================");
        JSONArray arr1 = JsonMain.metroLines(lineNumberLineName);
        System.out.println(arr1);
        System.out.println("\n listLinesAndStations ======================================================");
        JSONObject object = JsonMain.listLinesFndStations(lineNumberStationName, lineNumberLineName);
        System.out.println(object);

        System.out.println("\nJsonMainList {stations:[{names:,line:, date:, depth:, ... hasConnection ===============");
        GetHTML getHtml = new GetHTML();

        JSONObject mainJson;
        mainJson = JsonMain.JsonMainInformStation(linesNumbersLinesNames
                , nameStationNumberLine
                , listDate
                , listStationDepth
                , hasInterconnection);

        System.out.println(mainJson);

//        System.out.println("+++++++++++++++++++++++++++++");
//        System.out.println("size()  nameStationNumberLine " + nameStationNumberLine.size());
//        nameStationNumberLine.entrySet().stream().forEach(s -> System.out.println(s.getKey() + " " + s.getValue()));
//        System.out.println();
//        System.out.println("lineNumbersStationsNamesHashMap(){} =====================================");
//
//        GetHTML.GetStations getStations = new GetHTML().new GetStations();
//        System.out.println("size() " + getStations.lineNumbersStationsNamesHashMap().size());
//        System.out.println(getStations.lineNumbersStationsNamesHashMap());
//
//        System.out.println("\n==========================================");
//        System.out.println("hasInterconnection.size()  " + hasInterconnection.size());
//        System.out.println(hasInterconnection);
    }

}
