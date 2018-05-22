
/**
 * Assignment 3 GameLevel Creator.
 */
public class Ass3Game {

    /**
     * Creates the game object, initializes it and runs.
     * @param args nothing.
     */
    public static void main(String[] args) {
        LevelInformation levelTwo = new LevelTwo();
        GameLevel gameLevel = new GameLevel(levelTwo);
        gameLevel.initialize();
        gameLevel.run();
    }
}
