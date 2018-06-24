package levels;

import hitters.Velocity;
import sprites.BackgroundColor;
import sprites.BackgroundImage;
import sprites.Sprite;
import utilities.ColorsParser;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class that reads the file stream and extracts information about the level specification.
 */
public class LevelSpecificationReader {

    private List<LevelInformation> levels;
    private Map<String, String> levelArguments;
    private int lineNumber;

    /**
     * The object's constructor, simply initializes the level information list and their arguments map.
     */
    public LevelSpecificationReader() {

        this.levels = new ArrayList<LevelInformation>();
        this.levelArguments = new TreeMap<String, String>();
    }

    /**
     * Reads the level information from the file stream and saves it in an order we can use as a map.
     * @param reader the stream reader.
     * @return returns a list of levels.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader bufferedReader;
        String currentLine;
        lineNumber = 0;
        try {
            bufferedReader = new BufferedReader(reader);
            while ((currentLine = bufferedReader.readLine()) != null) {
                lineNumber++;
                if (!currentLine.isEmpty() && !currentLine.startsWith("#")) {
                    if (currentLine.equals("START_LEVEL") || currentLine.equals("END_LEVEL")) {
                        continue;
                    // Since START_BLOCKS always appears after definitions, it's ok to check and access it through elif.
                    } else if (currentLine.equals("START_BLOCKS")) {
                        try {
                            extractLevelDef(lineNumber + 1, bufferedReader);
                        } catch (Exception e) {
                            e.getMessage();
                            System.exit(1);
                        }
                    } else {
                        String[] lineSplit = currentLine.split(":");
                        levelArguments.put(lineSplit[0], lineSplit[1]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception: Failed reading file");
            e.printStackTrace();
        }
        return this.levels;
    }

    /**
     * An extracting helping method that extracts our needed information and adds blocks to the level accordingly.
     * @param lineNum the line we start reading from.
     * @param bufferedReader the buffered reader we use to read the information.
     * @throws Exception throws an exception in case of invalid information.
     */
    private void extractLevelDef(int lineNum, BufferedReader bufferedReader) throws Exception {
        if (!validityCheck()) {
            throw new Exception("Level file not defined properly");
        }
            Level newLevel = createLevel();
        int blockYPos = Integer.parseInt(this.levelArguments.get("row_height"));
        InputStreamReader is = null;
        int blockRow = 1;
        try {
            is = new InputStreamReader(ClassLoader.getSystemClassLoader().
                    getResourceAsStream(levelArguments.get("block_definitions")));
            BlocksFromSymbolsFactory blocks = BlocksDefinitionReader.fromReader(is);
            String currentLine = bufferedReader.readLine();
            while (!(currentLine.equals("END_BLOCKS"))) {
                if (!(currentLine.isEmpty()) && !(currentLine.startsWith("#"))) {
                    int xPos = Integer.parseInt(levelArguments.get("blocks_start_x"));
                    int yPos = Integer.parseInt(levelArguments.get("blocks_start_y")) + (blockRow * blockYPos);
                    double xOffSet = 0;
                    for (char chr : currentLine.toCharArray()) {
                        // Block
                        if (blocks.isBlockSymbol(String.valueOf(chr))) {
                            newLevel.addBlocks(blocks.getBlock(String.valueOf(chr), xPos + (int) xOffSet, yPos));
                            xOffSet += blocks.getBlock(String.valueOf(chr), 0, 0).getWidth();
                        // Space
                        } else if (blocks.isSpaceSymbol(String.valueOf(chr))) {
                            xOffSet += blocks.getSpaceWidth(String.valueOf(chr));
                        }
                    }
                }
                blockRow++;
                currentLine = bufferedReader.readLine();
            }
            levels.add(newLevel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed closing file");
            }
        }
    }

    /**
     * Specific method to extract ball velocities from the file (they're arranged differently in the file so we use a
     * different method).
     * @return returns a list of the ball's velocities.
     */
    private List<Velocity> extractVelocities() {
        String[] velTemp = levelArguments.get("ball_velocities").split(" ");
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (String vel : velTemp) {
            String[] velAndAngle = vel.split(",");
            Velocity velocity = Velocity.fromAngleAndSpeed(Double.parseDouble(velAndAngle[0]), Double
                    .parseDouble(velAndAngle[1]));
            velocityList.add(velocity);
        }
        return velocityList;
    }

    /**
     * Extracts the level's background from the file.
     * @return returns the background as a sprite.
     */
    private Sprite extractBackground() {
        String bgString = levelArguments.get("background");
        ColorsParser cParser = new ColorsParser();
        if (bgString.startsWith("color")) {
            Color bgColor = cParser.colorFromString(bgString);
            return new BackgroundColor(bgColor);
        } else {
            return new BackgroundImage(bgString.substring(6, bgString.length() - 1));
        }
    }


    /**
     * Creates a level using the information we extracted.
     * @return returns the completed level object.
     */
    private Level createLevel() {
        String name = levelArguments.get("level_name");
        List<Velocity> ballVelocities = extractVelocities();
        Sprite background = extractBackground();
        int paddleSpeed = Integer.parseInt(levelArguments.get("paddle_speed"));
        int paddleWidth = Integer.parseInt(levelArguments.get("paddle_width"));
        int numOfBlocks = Integer.parseInt(levelArguments.get("num_blocks"));
        int numOfBalls = ballVelocities.size();
        return new Level(name, ballVelocities, paddleSpeed, paddleWidth, background, numOfBlocks, numOfBalls);
    }

    /**
     * Checks if the file has all the strings that compose a level, if not we exit the program.
     * @return returns whether the file is valid or not.
     */
    private boolean validityCheck() {
        String[] definitions = {"level_name", "ball_velocities", "background", "paddle_speed", "paddle_width",
                "block_definitions", "blocks_start_x", "blocks_start_y", "row_height", "num_blocks"};
        for (String def : definitions) {
            if (!levelArguments.containsKey(def)) {
                System.out.println(def + " definition does not exist, parsing failed!");
                return false;
            }
        }
        return true;
    }

}
