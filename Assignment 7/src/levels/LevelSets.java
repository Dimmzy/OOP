package levels;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class that holds the sets of levels for each difficulty.
 */
public class LevelSets {

    /**
     * Creates a set holding levels.
     * @param reader reads the level information from the file.
     * @return returns a map of each level and it's difficulty.
     */
    public Map<String, SetInformation> createSet(java.io.Reader reader) {
        LineNumberReader lineReader = new LineNumberReader(reader);
        Map<String, SetInformation> setMap = new TreeMap<String, SetInformation>();
        try {
            String line = lineReader.readLine();
            String levelKey = "";
            String levelName = "";
            while (line != null) {
                if (lineReader.getLineNumber() % 2 == 1) {
                    String[] splitLine = line.split(":");
                    levelKey = splitLine[0];
                    levelName = splitLine[1];
                } else {
                    InputStreamReader in = null;
                    List<LevelInformation> levelList = null;
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                        in = new InputStreamReader(is);
                        levelList = new LevelSpecificationReader().fromReader(in);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Failed reading level file");
                    }
                    SetInformation set = new SetInformation(levelName, levelList);
                    setMap.put(levelKey, set);
                }
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed reading the level file");
        }
        return setMap;
    }
}
