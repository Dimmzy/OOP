import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 5 Driver Class.
 */
public class Ass5Game {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, ks);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
            gameFlow.runLevels(levels);
        } else {
            for (String arg : args) {
                        switch (arg) {
                            case ("1"):
                                levels.add(new LevelOne());
                                break;
                            case ("2"):
                                levels.add(new LevelTwo());
                                break;
                            case ("3"):
                                levels.add(new LevelThree());
                                break;
                            case ("4"):
                                levels.add(new LevelFour());
                                break;
                            default:
                                break;
                        }
                    }
                if (levels.isEmpty()) {
                    System.out.println("No valid levels passed, exiting!");
                    gui.close();
                    return;
                } else {
                    gameFlow.runLevels(levels);
                }

            }
            EndScreen end = new EndScreen(gameFlow.getGameCompleted(), gameFlow.getScore(), ks);
            animationRunner.run(end);
            gui.close();
        }

    /**
     * Checks if the argument passed to main is a string (parsable to int)
     * @param arg The string we check.
     * @return returns if parseable.
     */
    public boolean checkArg(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
