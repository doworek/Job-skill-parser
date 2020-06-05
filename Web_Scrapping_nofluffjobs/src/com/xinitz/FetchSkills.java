package com.xinitz;

import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;



public class FetchSkills {
    private Document doc;
    private Document docLink;
    private void print(String s){
        System.out.println(s);
    }

    public FetchSkills(String url) {
        try {
            doc = Jsoup.connect(url).get();
            String title = doc.title();
            print("Title: " + title);
            String offerTitle = doc.select("h1").text();
            print("offer title: " + offerTitle);

        } catch (IOException e) {
            print("Something went wrong");
            e.printStackTrace();
        }
    }

    protected void printRequiredLevel() {
        String level = doc.select("#posting-requirements > h3:nth-child(1)").text();
        print("Required level: " + level);
        String level2 = doc.select("nfj-posting-requirements.d-block:nth-child(2) > h3:nth-child(1)").text();
        print("Required level: " + level2);
    }

    String[] hrefTab = new String[50];
    int i = 0;
    protected String[] printLinkScrape(){
        Arrays.fill(hrefTab,"");
        Elements link = doc.select("a[href^=/pl/job/]"); //wszystkie a zawierające href zaczynające się na /pl/job/
        String absHref = link.attr("abs:href");
        for (Element el : link) {
            absHref = el.attr("abs:href");
           // print(el.text());
           // print(absHref);
            hrefTab[i] = absHref;
            i++;
        }
       // for (String s : hrefTab) {
        //    print(s);
        //}
        return hrefTab;
    }

    protected void printSkills() throws IOException {
        int i = 0;
        String[] linksOnSite = printLinkScrape();
        FileWriter fileWriter = null;

        try {
           try {
                //fileWriter = new FileWriter("skills" + n + ".txt");
               // fileWriter.write("Page nr: " + n + "\n\n");
               fileWriter = new FileWriter("skills.txt", true); //true not to overwrite

                for (String s : linksOnSite) {
                    try {
                       // print(linksOnSite[i]);
                        docLink = Jsoup.connect(linksOnSite[i]).get();
                    } catch (IOException e) {
                        print("Something went wrong");
                        e.printStackTrace();
                    }
                    Elements possibleLinks = docLink.select(".btn.btn-sm.btn-outline-success.text-truncate");
                    for (Element el : possibleLinks) {
                        // print("Skill: " + el.text());
                       // fileWriter.write("Offer number: " + i + " " + "Skill: " + el.text() + "\n");
                        fileWriter.write( el.text() + "\r\n");
                        fileWriter.flush();
                    }
                    if(linksOnSite[i+1] != ""){
                    i++;
                    } else{
                        break;
                    }
                }
            }finally {
                       fileWriter.close();
               }
            }catch (IOException e) {
                print("An error occurred.");
                e.printStackTrace();
            }

        }
    }


