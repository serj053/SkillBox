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
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    public static void main(String[] args) throws IOException, ParseException {




 /*получаем со страницы данные - линии Московского метро и ее номер
         *                             - станции московского метро и  номер линии*/
        System.out.println("*получаем со страницы данные\n " +
                "- линии Московского метро и ее номер");
        GetHTML getHTML = new GetHTML();
        HashMap<String, String> lineNumberLineName ;
        lineNumberLineName = getHTML.new GetLines().linesNumbersLinesNames();
        lineNumberLineName.entrySet().stream()
                .map(m -> {
                    System.out.println(m.getKey() + "  " + m.getValue());
                    return 0;
                }).toList();

        System.out.println();
        System.out.println("-название станций и номер линий------------------------------------");
        System.out.println();
        ArrayList<String> lineNumberStationName = getHTML.new GetStations().lineNumbersStationsNames();
        lineNumberStationName.forEach(System.out::println);
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

 /*Обработка CSV файлов */
        System.out.println("выводим значение глубины станции из CSV файла");
        //создаем объект для парсинга
        stationsDepth stationsDepth = new stationsDepth();
        HashMap<String, String> listJs = new HashMap<>();
        //передаем в метод путь к JSON файлу и массив для  новых объектов и
        // получаем список объектов полученных из Json файла
        stationsDepth.getDepth(listJs, listJsnPath.get(0));
        stationsDepth.getDepth(listJs, listJsnPath.get(1));
        HashMap<String, String> listStationDepth = stationsDepth.getDepth(listJs, listJsnPath.get(2));
        System.out.println("++++++++++++++ " + listStationDepth.keySet().size()+ " +++++++++++");
       for(String str : listStationDepth.keySet()){
           System.out.println(str + " " + listStationDepth.get(str));
       }

        System.out.println("\n");

        /*Парсинг CSV фала - выводим объекты полученные из CSV файла*/
        System.out.println("/*Парсинг CSV фала - выводим даты постройки станций полученные из CSV файла*/");
        System.out.println("listCsv.get(1) " + listCsv.get(1));
        //получаем список с объектами
        ArrayList<CsvParse.StationDate> stationDate = new ArrayList<>();
        ArrayList<CsvParse.StationDate> listDate;
        CsvParse csvParse = new CsvParse();
        csvParse.stationLaunchDate(stationDate, listCsv.get(0));
        csvParse.stationLaunchDate(stationDate, listCsv.get(1));
        listDate = csvParse.stationLaunchDate(stationDate, listCsv.get(2));
        /*форматируем дату*/
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd.MM.yyyy");
        AtomicInteger nn = new AtomicInteger();
        listDate.forEach(o -> System.out.println(nn.getAndIncrement() + "  " +
                o.getStationName() + "  " + simpleFormat.format(o.getDate())));
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
        //ArrayList<String> stationsNamesLines = new ArrayList<>();
        getLine = html.new GetLines().linesNumbersLinesNames();
        //stationsNamesLines = html.new GetStations().stationsNamesLinenumbers();
        for (int i = 0; i < lineNumberStationName.size(); i++) {//берем текущую станцию
            for (int j = 1; j < lineNumberStationName.size(); j++) {//берем следующую станцию
                String str1 = lineNumberStationName.get(i).substring(11);//подстрока название первой станции
                String str11 = lineNumberStationName.get(i).substring(8, 9);//подстрока номер первой линии
                String str2 = lineNumberStationName.get(j).substring(11);//подстрока название второй станции
                String str22 = lineNumberStationName.get(j).substring(8, 9);//подстрока номер второй линии
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
        System.out.println("\nlistLinesWithStations  ****************************************************");

        JSONObject result  = new JSONObject();
        result = JsonMain.listLinesWithStations(lineNumberStationName);
        System.out.println(result);

        System.out.println("\ntransferStations  ****************************************************");
        JSONArray arr = JsonMain.transferStations(lineNumberStationName);
        System.out.println(arr);

        System.out.println("metroLines  ======================================================");
        JSONArray arr1 = JsonMain.metroLines(lineNumberLineName);
        System.out.println(arr1);
        System.out.println("  Result======================================================");
        JSONObject object = JsonMain.listStationsAndLines(lineNumberStationName, lineNumberLineName);
        System.out.println(object);

        System.out.println("JsonMain ================================================");
        JSONObject mainJson = new JSONObject();
        mainJson = JsonMain.JsonMainInformStation(lineNumberStationName, listDate, listStationDepth);
        System.out.println(mainJson);
    }

}
