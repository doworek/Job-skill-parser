package com.xinitz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Ready.....\n");
        Map<String, Integer> words = new HashMap<String, Integer>();
        CountOccurrences countOccurrences = new CountOccurrences();

        for (int i=1; i<10; i++) {
        String url = "https://nofluffjobs.com/pl/jobs/frontend?page=" + i;
        FetchSkills fetchSkills = new FetchSkills(url);
        try {
            fetchSkills.printSkills();
            System.out.println("Page: " + i + "\n");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        }

        try {
                FileWriter fileWriter = null;
                countOccurrences.countEachSkill("skills.txt", words);
            try {
                fileWriter = new FileWriter("skillsNumb.txt");
                        // System.out.println(words);
                        fileWriter.write( words + "\n");
                        fileWriter.flush();
            }finally {
                fileWriter.close();
            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Most wanted skill: " + countOccurrences.getMaxEntryInMapBasedOnValue(words));
    }
}
