package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class JsParse {
    private final JSONParser parser;

    /*в конструктор передается путь рассматриваемого файла */
    public JsParse(){
        this.parser = new JSONParser();
    }

    public List<StationDepth> getJsonObjects(ArrayList<StationDepth> list, String file)
            throws IOException, ParseException {
        FileReader read = new FileReader(file);

        JSONArray array = (JSONArray) parser.parse(read);
        for (Object obj : array) {
            JSONObject jobj = (JSONObject) obj;
            String name = (String) jobj.get("station_name");
            String depth = jobj.get("depth").toString();
            if (list.size() == 0) {
                list.add(new StationDepth(name, depth));
                continue;
            }
            int flag = 0;
            /*отсекание повторяющихся значенией*/
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).station.equals(name)) {

                    flag++;
                }
            }
            if (flag == 0) {
                list.add(new StationDepth(name, depth));
            }

        }
        return list;
    }

    static class StationDepth {
        private final String station;
        private final String depth;

        public StationDepth(String s, String d) {
            this.station = s;
            this.depth = d;
        }

        public String getStation() {
            return station;
        }

        public String getDepth() {
            return depth;
        }

        @Override
        public String toString() {
            return "StationDepth{" +
                    "station='" + station + '\'' +
                    ", depth=" + depth +
                    '}';
        }


    }

}
