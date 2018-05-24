import animation.AnimationRunner;
import animation.EndScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamelogic.GameFlow;
import levels.LevelInformation;
import levels.LevelOne;
import levels.LevelTwo;
import levels.LevelThree;
import levels.LevelFour;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 5 Driver Class.
 */
public class Ass5Game {

    /**
     * The main method that creates the GUI and supplements and runs tells GameFlow to run the levels according to
     * the main's received arguments.
     * @param args The level's order. (Ignores anything above 4, below 1 and non numerals)
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, ks);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        // If no arguments received simply runs the levels in order.
        if (args.length == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
            gameFlow.runLevels(levels);
        } else {
            // Iterates through the arguments and appends the levels if their numeral exists in the arg list.
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
                    // If there weren't any 1-4 numbers in the arguments, exit with an error message.
                if (levels.isEmpty()) {
                    System.out.println("No valid levels passed, exiting!");
                    gui.close();
                    return;
                } else {
                    gameFlow.runLevels(levels);
                }

            }
            // Draws the game end screen (gets a boolean value indicating whether the game was won or not from gameFlow)
            EndScreen end = new EndScreen(gameFlow.getGameCompleted(), gameFlow.getScore(), ks);
            animationRunner.run(end);
            gui.close();
        }

}
