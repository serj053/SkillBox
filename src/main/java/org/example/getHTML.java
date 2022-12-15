package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class getHTML {
Document doc;
    public getHTML() {
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
      ArrayList getMetroLine() {
          ArrayList<String> list = new ArrayList<>();

          Elements elements = doc.select("span.js-metro-line");
          String name;
          String number;
          String result;
          for(Element element: elements){
              StringBuilder builder = new StringBuilder();
              name = element.select("span.js-metro-line").html();
              number = element.attr("data-line");
              result = builder.append(name +" ").append(number).toString();
              list.add(result);
          }
            return list;
        }
    }

    class GetStations {
        ArrayList getMetroStation() {
            ArrayList<String> list = new ArrayList<>();

            Elements elements = doc.select("span.js-metro-line");
            String name;
            String number;
            String result;
            for(Element element: elements){
                StringBuilder builder = new StringBuilder();
                name = element.select("span.js-metro-line").html();
                number = element.attr("data-line");
                result = builder.append(name +" ").append(number).toString();
                list.add(result);
            }
            return list;
        }
    }

}
