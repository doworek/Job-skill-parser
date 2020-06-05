package com.xinitz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by         : Chanaka Fernando.
 * Date               : Tue, 6/26/2018 .
 * Email              : nuwan.c.fernando@gmail.com.
 * LinkedIn           : https://www.linkedin.com/in/n-chanaka-fernando
 * Belongs to Project : Web_scrapping_with_jsoup.
 * Package            : com.xinitz.
 */
public class FetchAllTheCountriesOfWorld {

   private static FetchAllTheCountriesOfWorld mInstance;
   private Document doc;

    public FetchAllTheCountriesOfWorld() {
        try {
            doc = Jsoup.connect("https://www.countries-ofthe-world.com/all-countries.html").get();
            String title = doc.title();
            print("Title: "+title);

        } catch (IOException e) {
            print("Something went wrong");
            e.printStackTrace();
        }
    }

    protected void printDescription() {
        String descTitle = doc.select(".content-text").select("h1").text();
        print("The description title: " + descTitle);
        String desc = doc.select(".content-text").select("p").text();
        print("The descrption: " + desc);
    }

    protected void printAllCountries() {
        Elements countries = doc.select("ul.column").select("li:not(.letter)");
        for(Element el: countries) {
            print(el.text());
        }
    }

    protected void printCountriesByFirstLetter(String letter) {
        Elements countriesByLetter = doc.select("ul.column").select("li:not(.letter)").select("li:matches(^"+letter.toUpperCase()+")");

        print("Countries by letter " + letter +": ");
        for(Element el: countriesByLetter) {
            print(el.text());
        }
    }

    private void print(String s){
        System.out.println(s);
    }


    public static FetchAllTheCountriesOfWorld getmInstance(){
        if(mInstance == null) {
            mInstance = new FetchAllTheCountriesOfWorld();
        }
        return mInstance;
    }
}
