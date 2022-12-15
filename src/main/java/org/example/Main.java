package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Document doc;
        try {
            doc = Jsoup.connect("https://skillbox-java.github.io").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements elements = doc.select("div.js-metro-stations");
        //System.out.println(elements.attr("data-depend-set"));
        for (Element el : elements) {
            String number = el.attr("data-line");
            System.out.println(number + "  ******************************");
//            System.out.println(number + " "
//                    + el.select("span.name"));
            Elements elements2 = el.select("span.name");
            for(Element el2 : elements2){
                System.out.println(el2.text());
            }
        }
        //System.out.println(doc.html());


//        getHTML html = new getHTML();
//        ArrayList metroLine;
//        metroLine = html. new GetLines().getMetroLine();
//        metroLine.forEach(System.out::println);
//
//        Elements elements = html.doc.select("span.js-metro-line");
//        for(Element el: elements){
//            System.out.print(el.select("span.js-metro-line").html() + "  ");
//            System.out.println(el.attr("data-line"));
//        }

        //System.out.println(html.doc.select("span.js-metro-line").attr("data-line"));
        //System.out.println(html. new GetLines().getMetroLine());


    }
}