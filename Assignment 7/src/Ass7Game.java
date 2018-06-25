import animation.Animation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import animation.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DialogManager;
import gamelogic.GameFlow;
import scores.HighScoresTable;
import scores.ScoreInfo;

import java.io.File;
import java.io.IOException;

/**
 * Assignment 5 Driver Class.
 */
public class Ass7Game {

    private static final String HS_FILE_NAME = "highscores.txt";

    /**
     * The main method that creates the GUI and supplements and runs tells GameFlow to run the levels according to
     * the main's received arguments.
     *
     * @param args The level's order. (Ignores anything above 4, below 1 and non numerals)
     */
    public static void main(String[] args) {
        HighScoresTable highScores = new HighScoresTable(10);
        try {
            highScores = HighScoresTable.loadFromFile(new File(HS_FILE_NAME));
        } catch (IOException e) {
            System.out.println("I/O Exception: Failed loading highscores table from file");
        }
        // Converts the high scores variable to final so we can access it from the anonymous classes.
        final HighScoresTable hsTable = highScores;
        final GUI gui = new GUI("Space Invaders", 800, 600);
        DialogManager dialog = gui.getDialogManager();
        final AnimationRunner animationRunner = new AnimationRunner(gui);
        final KeyboardSensor ks = gui.getKeyboardSensor();
        final GameFlow gameFlow = new GameFlow(animationRunner, ks);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Space Invaders", ks);
        menu.addSelection("s", "Start Game", new Task<Void>() {
            @Override
            public Void run() {
                gameFlow.runLevel();
                Animation end = new KeyPressStoppableAnimation(ks, "space",
                        new EndScreen(gameFlow.getScore(), gameFlow.getLevelsCleared()));
                animationRunner.run(end);
                if (hsTable.getRank(gameFlow.getScore()) < hsTable.size()) {
                    String playerName = dialog.showQuestionDialog("Name", "What is your name?", "");
                    if (hsTable.size() < 10) {
                        hsTable.add(new ScoreInfo(playerName, gameFlow.getScore()));
                    } else {
                        hsTable.getHighScores().remove(9);
                        hsTable.add(new ScoreInfo(playerName, gameFlow.getScore()));
                    }
                    try {
                        hsTable.save(new File(HS_FILE_NAME));
                    } catch (IOException e) {
                        System.out.println("I/O Exception: Failed saving new player into high scores file");
                    }
                }
                gameFlow.restart();
                animationRunner.run(
                        new KeyPressStoppableAnimation(ks, "space", new HighScoresAnimation(hsTable)));
                return null;
            }
        });
        menu.addSelection("h", "Highscores", new Task<Void>() {
            @Override
            public Void run() {
                animationRunner.run(new KeyPressStoppableAnimation(ks, "space", new HighScoresAnimation(hsTable)));
                return null;
            }
        });
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });

        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            ((MenuAnimation<Task<Void>>) menu).restart();
        }
    }
}
