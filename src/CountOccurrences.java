import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CountOccurrences {
    final static Logger logger = Logger.getLogger(CountOccurrences.class);

    void countEachSkill(String fileName, Map<String, Integer> words) throws FileNotFoundException {
        BasicConfigurator.configure();
        Scanner file = new Scanner(new File(fileName));
        //Checking if next line exists and then counting words
             try {
            while (file.hasNextLine()) {
                String word = file.nextLine();
                Integer count = words.get(word);
                if (count != null) {
                    count++;
                } else {
                    count = 1;
                }
                words.put(word, count);
            }
        }catch (ClassCastException e) {
            logger.error("Error - Klucz jest nieodpowiedniego typu dla tej mapy"); // words.get/put
        }catch (NullPointerException e){
            logger.error("Error - Jesli klucz ma wart NULL, a mapa nie zezwala na uzycie klucza NULL"); // words.get/put
        }catch (UnsupportedOperationException e){
            logger.error("Error - Jesli operacja put nie jest obslugiwana przez te mape"); // words.put
        }catch (IllegalArgumentException e) {
            logger.error("Error - Jeśli jakaś właściwość określonego klucza lub wartości uniemożliwia zapisanie jej na tej mapie"); // words.put
        }
        file.close();
        logger.debug("Closing file\n");
    }
    public static <K, V extends Comparable<V> > Map.Entry<K, V>
    getMaxEntryInMapBasedOnValue(Map<K, V> map)
    {        // To store the result
        Map.Entry<K, V> entryWithMaxValue = null;
        // Iterate in the map to find the required entry
        try{
        for (Map.Entry<K, V> currentEntry : map.entrySet()) {
            if (entryWithMaxValue == null || currentEntry.getValue().compareTo(entryWithMaxValue.getValue()) > 0) {
                entryWithMaxValue = currentEntry;
            }
        }}
        catch (IllegalStateException e){
            logger.error("Error - implementacje mogą, ale nie muszą, zgłaszać ten wyjątek, jeśli wpis został usunięty z backing map"); //mapEntry.getvalue
        }
        // Return the entry with highest value
        return entryWithMaxValue;
    }
}
