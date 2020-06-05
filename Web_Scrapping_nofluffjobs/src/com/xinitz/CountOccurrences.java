package com.xinitz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class CountOccurrences {

    void countEachSkill(String fileName, Map<String, Integer> words) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        while (file.hasNextLine()){
            String word = file.nextLine();
            Integer count = words.get(word);
            if (count != null){
                count++;
            }else{
                count = 1;
            }
            words.put(word, count);
        }
        file.close();
    }
    public static <K, V extends Comparable<V> > Map.Entry<K, V>
    getMaxEntryInMapBasedOnValue(Map<K, V> map)
    {        // To store the result
        Map.Entry<K, V> entryWithMaxValue = null;
        // Iterate in the map to find the required entry
        for (Map.Entry<K, V> currentEntry : map.entrySet()) {
            if (
                // If this is the first entry, set result as this
                    entryWithMaxValue == null
                            // If this entry's value is more than the max value
                            // Set this entry as the max
                            || currentEntry.getValue()
                            .compareTo(entryWithMaxValue.getValue())
                            > 0) {
                entryWithMaxValue = currentEntry;
            }
        }
        // Return the entry with highest value
        return entryWithMaxValue;
    }
}
