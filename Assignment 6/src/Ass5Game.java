import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DialogManager;
import gamelogic.GameFlow;
import levels.LevelInformation;
import levels.LevelOne;
import levels.LevelTwo;
import levels.LevelThree;
import levels.LevelFour;
import scores.HighScoresTable;
import scores.ScoreInfo;
import animation.Animation;
import tasks.ShowHiScoresTask;
import tasks.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 5 Driver Class.
 */
public class Ass5Game {

    private static final String HS_FILE_NAME = "highscores.txt";

    /**
     * The main method that creates the GUI and supplements and runs tells GameFlow to run the levels according to
     * the main's received arguments.
     * @param args The level's order. (Ignores anything above 4, below 1 and non numerals)
     */
    public static void main(String[] args) {
        HighScoresTable highScores = new HighScoresTable(10);
        try {
            highScores = HighScoresTable.loadFromFile(new File(HS_FILE_NAME));
        } catch (IOException e) {
            System.out.println("I/O Exception: Failed loading highscores table from file");
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        DialogManager dialog = gui.getDialogManager();
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
            }

        }

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid",ks,animationRunner);
        Animation scoreAnimation = new KeyPressStoppableAnimation(ks, "h",new HighScoresAnimation(highScores,
                "h"));
        menu.addSelection("h", "Hi scores", new ShowHiScoresTask(animationRunner, scoreAnimation));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            break;
        }
        // Draws the game end screen (gets a boolean value indicating whether the game was won or not from gameFlow)
        Animation end = new KeyPressStoppableAnimation(ks,"h", new EndScreen
                (gameFlow.getGameCompleted(), gameFlow.getScore()));
        animationRunner.run(end);
        if (highScores.getRank(gameFlow.getScore()) < highScores.size()) {
            String playerName = dialog.showQuestionDialog("Name", "What is your name?", "");
            highScores.add(new ScoreInfo(playerName, gameFlow.getScore()));
            try {
                highScores.save(new File(HS_FILE_NAME));
            } catch (IOException e) {
                System.out.println("I/O Exception: Failed saving new player into high scores file");
            }
        }
        Animation displayHS = new KeyPressStoppableAnimation(ks,"h",
                new HighScoresAnimation(highScores, "h"));
        animationRunner.run(displayHS);
        gui.close();
    }

}
