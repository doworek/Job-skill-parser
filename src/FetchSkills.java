import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FetchSkills {
    final static Logger logger = Logger.getLogger(FetchSkills.class);
    private Document doc;
    private Document docLink;

    public FetchSkills(String url) {
        BasicConfigurator.configure();
        try {
            logger.debug("Connecting to an URL\n");
            doc = Jsoup.connect(url).timeout(60 * 1000).get();
            logger.info("Connected to an URL\n");
        }catch (SocketTimeoutException e) {
            logger.error("Timeout, couldn't connect");
        }catch (HttpStatusException e) {
            logger.fatal("Page not found");
        }catch (IOException e){
        logger.error("Something went wrong");
        e.printStackTrace();
    }
}

    String[] hrefTab = new String[50];
    int i = 0;
    protected String[] printLinkScrape(){
        Arrays.fill(hrefTab,"");
        Elements link = doc.select("a[href^=/pl/job/]"); //wszystkie a zawierające href zaczynające się na /pl/job/
        String absHref = link.attr("abs:href");
        for (Element el : link) {
            absHref = el.attr("abs:href");
            hrefTab[i] = absHref;
            i++;
        }
        return hrefTab;
    }

    protected void printSkills() throws IOException {
        int i = 0;
        String[] linksOnSite = printLinkScrape();
        FileWriter fileWriter = null;

        logger.info("Printing skills\n");
        try {
            fileWriter = new FileWriter("skills.txt", true); //true not to overwrite

            for (String s : linksOnSite) {
                try {
                    docLink = Jsoup.connect(linksOnSite[i]).get();
                } catch (IOException e) {
                    logger.error("Something went wrong");
                    e.printStackTrace();
                }
                Elements possibleLinks = docLink.select(".btn.btn-sm.btn-outline-success.text-truncate");
                for (Element el : possibleLinks) {
                    fileWriter.write( el.text() + "\r\n");
                    fileWriter.flush();
                }
                if(linksOnSite[i+1] != ""){
                    i++;
                } else{
                    break;
                }
            }
        }catch (IOException e) {
            logger.error("Error while printing skills\n");
            e.printStackTrace();
        }finally {
            logger.debug("Closing fileWriter\n");
            fileWriter.close();
        }
    }
}
