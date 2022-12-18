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

public class JsParse {
    private final FileReader read;
    private final JSONParser parser;

    /*в конструктор передается путь рассматриваемого файла */
    public JsParse(String jsonFile) throws FileNotFoundException {
        this.read = new FileReader(jsonFile);
        this.parser = new JSONParser();
    }

    public List<StationDepth> getJsonObjects() throws IOException, ParseException {
        List<StationDepth> list = new ArrayList<>();
        JSONArray array = (JSONArray) parser.parse(read);
        for (Object obj : array) {
            JSONObject jobj = (JSONObject) obj;
            String name = (String) jobj.get("station_name");
            String depth = jobj.get("depth").toString();
           // System.out.println(depth + "  " + name);
            list.add(new StationDepth(name, depth));
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
        @Override
        public String toString() {
            return "StationDepth{" +
                    "station='" + station + '\'' +
                    ", depth=" + depth +
                    '}';
        }
    }

}
