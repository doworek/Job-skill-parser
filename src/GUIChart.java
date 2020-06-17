import java.awt.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class GUIChart {
    Map<String, Integer> map;
    CountOccurrences countOccurrences;
    private List<Color> colors = new ArrayList<Color>();
    public static final Color LIGHT_RED = new Color(255,102,102);
    public static final Color LIGHT_ORANGE = new Color(255, 153, 0);
    public static final Color LIGHT_YELLOW = new Color(255, 255, 153);
    public static final Color LIGHT_GREEN = new Color(102, 255, 102);
    public static final Color LIGHT_BLUE = new Color(51, 204, 255);
    public static final Color LIGHT_GREY= new Color(204, 204, 204);


    public GUIChart(Map<String, Integer> map, CountOccurrences countOccurrences){
        this.map=map;
        this.countOccurrences = countOccurrences;
    }

    public void createAndShowGUI()
    {
        colors = List.of(LIGHT_RED, LIGHT_ORANGE, LIGHT_YELLOW, LIGHT_GREEN, LIGHT_BLUE, LIGHT_GREY);

        //before JAVA 9
        /*colors.add(LIGHT_RED);
        colors.add(LIGHT_ORANGE);
        colors.add(LIGHT_YELLOW);
        colors.add(LIGHT_GREEN);
        colors.add(LIGHT_BLUE);
        colors.add(LIGHT_GREY);*/

        BarChart panel = new BarChart();
        for(Color color : colors)
        {
            panel.addBarChartColumn(countOccurrences.getMaxEntryInMapBasedOnValue(map).getKey(), countOccurrences.getMaxEntryInMapBasedOnValue(map).getValue(), color);
            map.remove(countOccurrences.getMaxEntryInMapBasedOnValue(map).getKey());
        }
        panel.layoutBarChart();

        MyFrame frame = new MyFrame();
        frame.add( panel );
    }
}
