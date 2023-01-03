package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetHTML {
Document doc;
    public GetHTML() {
        String address = "https://skillbox-java.github.io/";
        Document doc;
        try {
            doc = Jsoup.connect(address).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.doc = doc;
    }

 class GetLines {

      HashMap<String, String> getMetroLine() {
          HashMap<String, String> list = new HashMap<>();

          Elements elements = doc.select("span.js-metro-line");
          String lineName;
          String lineNumber;
          for(Element element: elements){
              lineNumber = element.attr("data-line");
              lineName = element.select("span.js-metro-line").text();
             // if(lineNumber.length() != 0 && lineName.length() != 0) {
                  list.put(lineNumber, lineName);
             // }
          }
            return list;
        }
    }

    class GetStations {
        ArrayList getMetroStation() {
            ArrayList<String> list = new ArrayList<>();
            String line;
            Elements names = doc.select("div.js-metro-stations");
            for (Element el : names) {
                String lineNumber = el.attr("data-line");
                Elements elements2 = el.select("span.name");
                for(Element el2 : elements2){
                    line = "Линия  №" + lineNumber + "  "  +el2.text();
                    list.add(line);
                }
            }
            return list;
        }
    }

}
