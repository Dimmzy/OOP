package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Reads the block definitions from file.
 */
public class BlocksDefinitionReader {


    /**
     * Uses the java reader object to read the block's definitions from file.
     * @param reader the reader that reads the block def.
     * @return returns a symbol factory that can create our level blocks.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {

        Map<String, String> defaultDef = new TreeMap<String, String>();
        Map<String, String> blockDef = new TreeMap<String, String>();
        Map<String, Integer> spacerDef = new TreeMap<String, Integer>();
        Map<String, String> completeDef = new TreeMap<String, String>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        BlocksFromSymbolsFactory blocksFactory = new BlocksFromSymbolsFactory();

        String currentLine;
        try {
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (!(currentLine.isEmpty()) && !(currentLine.startsWith("#"))) {
                    if (currentLine.startsWith("default")) {
                        extractMapping(currentLine.substring("default".length()).trim(), defaultDef);
                    } else if (currentLine.startsWith("bdef")) {
                        extractMapping(currentLine.substring("bdef".length()).trim(), blockDef);
                        completeDef.putAll(defaultDef);
                        completeDef.putAll(blockDef);
                        try {
                             blocksFactory.addBlock(completeDef);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        blockDef.clear();
                    } else if (currentLine.startsWith("sdef")) {
                        String[] splitString = currentLine.split(" ");
                        String symbol = splitString[1].split(":")[1];
                        int width = Integer.parseInt(splitString[2].split(":")[1]);
                        spacerDef.put(symbol, width);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        blocksFactory.addSpacer(spacerDef);
        return blocksFactory;
    }

    /**
     * Extracts the mapping using string manipulation.
     * @param s The string we manipulate.
     * @param defaultDef the map we'll extract to.
     */
    private static void extractMapping(String s, Map<String, String> defaultDef) {
        String[] splitString = s.split(" ");
        for (String str : splitString) {
            String[] splitStringTwo = str.split(":");
            defaultDef.put(splitStringTwo[0], splitStringTwo[1]);
        }
    }

}
