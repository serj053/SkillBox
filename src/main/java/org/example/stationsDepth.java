package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class stationsDepth {
    private final JSONParser parser;

    /*в конструктор передается путь рассматриваемого файла */
    public stationsDepth() {
        this.parser = new JSONParser();
    }

    public HashMap<String, String> getDepth(HashMap<String, String> list, String file)
            throws IOException, ParseException {
        FileReader read = new FileReader(file);

        JSONArray array = (JSONArray) parser.parse(read);
        for (Object obj : array) {
            JSONObject jobj = (JSONObject) obj;
            String name = (String) jobj.get("station_name");
            String depth = jobj.get("depth").toString();
            list.put(name, depth);
        }
        return list;
    }

}
