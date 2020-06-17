import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import java.awt.*;
import java.nio.file.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class);
        BasicConfigurator.configure(); // konfiguracja, zapewnia logowanie do konsoli
        Map<String, Integer> words = new HashMap<String, Integer>();
        CountOccurrences countOccurrences = new CountOccurrences();
        ExecutorService executor = Executors.newFixedThreadPool(10);//creating a pool of threads

        logger.info("Ready to start...\n");
        
        // create an object of Path
        Path pathOfSkills = Paths.get("skills.txt");
        Path pathOfSkillsNumb = Paths.get("skillsNumb.txt");

        try {
            Files.deleteIfExists(pathOfSkills);
            Files.deleteIfExists(pathOfSkillsNumb);
            logger.debug("Creating a new file.");

            for (int i = 1; i <= 10; i++) {
                Runnable parse = new Parse("Parsing page " + i, i);
                executor.execute(parse);//calling execute method of ExecutorService
            }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
            logger.info("Done parsing");
        }catch (IndexOutOfBoundsException e) {
            logger.error("Caught IndexOutOfBoundsException: ");
        }catch (IOException e) {
            e.printStackTrace();
        }

        logger.debug("Creating filewriter\n");
        try {
            FileWriter fileWriter = null;
            countOccurrences.countEachSkill("skills.txt", words);
            try {
                fileWriter = new FileWriter("skillsNumb.txt");
                fileWriter.write(words + "\n");
                fileWriter.flush(); // oproznia strumien
            }catch (Exception e){
                logger.error("Error");
            }finally {
                fileWriter.close();
                logger.debug("Closing filewriter");
            }
        } catch (IOException e) {
            logger.error("Error");
            e.printStackTrace();
        }

        GUIChart gui = new GUIChart(words, countOccurrences);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.createAndShowGUI();
            }
        });
    }
}
