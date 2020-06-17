
import java.io.IOException;

public class Parse implements Runnable {

    String string;
    int i;

        public Parse(String string, int i) {
            this.string=string;
            this.i=i;
        }

        public void run() {
            Thread.currentThread().setName(this.string);

                String url = "https://nofluffjobs.com/pl/jobs/frontend?page=" + i;
                FetchSkills fetchSkills = new FetchSkills(url);
                try {
                    fetchSkills.printSkills();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
        }
    }