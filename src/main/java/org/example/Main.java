package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
       getHTML html = new getHTML();
        ArrayList<String>  lineList = html. new GetLines().getMetroLine();
       for(String str : lineList){
           System.out.println(str);
       }

        System.out.println("\n********************************************\n");
        ArrayList<String> stationsList = html. new GetStations().getMetroStation();
        stationsList.forEach(System.out::println);

    }
}