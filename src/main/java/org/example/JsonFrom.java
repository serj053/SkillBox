package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonFrom {

    public static JSONObject listStationsAndLines(ArrayList<String> list,
                                                  ArrayList<String> getStations,
                                                  HashMap<String, String> map) {
        JSONObject result = new JSONObject();
        result.put("stations", listLinesStations(list));
        result.put("connections", listConnections(getStations));
        result.put("lines", metroLines( map));

        return result;
    }

    public static JSONObject listLinesStations(ArrayList<String> list) {
        int startNumberPos = 8;
        int endNumberPos = 10;
        int nameStationPos = 11;
        JSONObject jsObj = new JSONObject();
        JSONArray jsArr = new JSONArray();
        String number = "";
        for (int i = 0; i < list.size(); i++) {

            if (jsArr.size() == 0) {
                jsArr.add(list.get(i).substring(nameStationPos));
                continue;
            }
            number = list.get(i).substring(startNumberPos, endNumberPos);
            if (number.equals(list.get(i - 1).substring(startNumberPos, endNumberPos))) {
                jsArr.add(list.get(i).substring(nameStationPos));
                if (i + 1 == list.size()) {
                    jsObj.put(number, jsArr);
                    break;
                }
            } else {
                jsObj.put(number, jsArr);
                jsArr = new JSONArray();
                jsArr.add(list.get(i).substring(nameStationPos));
            }
        }
        return jsObj;
    }

    public static JSONArray listConnections(ArrayList<String> getStations) {
        JSONObject obj = new JSONObject();
        JSONArray connections = new JSONArray();
        /*создаем массив массивов где каждый массив это два объекта*/
        for (int i = 0; i < getStations.size(); i++) {//берем текущую станцию
            for (int j = 1; j < getStations.size(); j++) {//берем следующую станцию
                String str1 = getStations.get(i).substring(11);//подстрока название первой станции
                String str11 = getStations.get(i).substring(8, 9);//подстрока номер первой линии
                String str2 = getStations.get(j).substring(11);//подстрока название второй станции
                String str22 = getStations.get(j).substring(8, 9);//подстрока номер второй линии
                if (str1.equals(str2) && !str11.equals(str22)//имена ровны номера не ровны ll
                ) {
                    JSONObject lineStation1 = new JSONObject();
                    JSONObject lineStation2 = new JSONObject();
                    JSONArray connect = new JSONArray();
                    lineStation1.put("line", str11);
                    lineStation1.put("station", str1);
                    lineStation2.put("line", str22);
                    lineStation2.put("station", str2);
                    connect.add(lineStation1);
                    connect.add(lineStation2);
                    connections.add(connect);
                }
            }
        }
        //obj.put("connections", connections);
        return connections;
    }

    /*получаем массив объектов содержвщих номер линии и ее название*/
    public static JSONArray metroLines(HashMap<String, String> map) {
        JSONArray jsArr = new JSONArray();
        JSONObject result = new JSONObject();

        for (String str : map.keySet()) {
            JSONObject obj = new JSONObject();
            obj.put("number", str);
            obj.put("name", map.get(str));
            jsArr.add(obj);
        }
        //result.put("lines", jsArr);
        return jsArr;
    }


}
