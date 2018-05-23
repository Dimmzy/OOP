
/**
 * Assignment 3 GameLevel Creator.
 */
public class Ass3Game {

    /**
     * Creates the game object, initializes it and runs.
     * @param args nothing.
     */
    public static void main(String[] args) {
        LevelInformation currLevel = new LevelFour();
        GameLevel gameLevel = new GameLevel(currLevel);
        gameLevel.initialize();
        gameLevel.run();
    }
}
