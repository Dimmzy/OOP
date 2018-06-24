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
import levels.LevelSets;
import levels.SetInformation;
import scores.HighScoresTable;
import scores.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Assignment 5 Driver Class.
 */
public class Ass6Game {

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
        // Converts the highscores variable to final so we can access it from the anonymous classes.
        final HighScoresTable hsTable = highScores;
        final GUI gui = new GUI("Arkanoid", 800, 600);
        DialogManager dialog = gui.getDialogManager();
        final AnimationRunner animationRunner = new AnimationRunner(gui);
        final KeyboardSensor ks = gui.getKeyboardSensor();
        Map<String, SetInformation> levelSet = null;
        final GameFlow gameFlow = new GameFlow(animationRunner, ks);
        String levelsFile;
        InputStreamReader in = null;
        if (args.length == 0) {
            levelsFile = "level_sets.txt";
        } else {
            levelsFile = args[0];
        }

        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelsFile);
            in = new InputStreamReader(is);
            levelSet = new LevelSets().createSet(in);
        } catch (Exception e) {
            System.out.println("Can't read from file");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Can't close the file");
                }
            }
        }
        final Map<String, SetInformation> completeSet = levelSet;
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", ks);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Level Menu", ks);
        menu.addSubMenu("s", "Play a Game", subMenu);
        if (completeSet != null) {
            for (Map.Entry<String, SetInformation> map : completeSet.entrySet()) {
                final Map.Entry<String, SetInformation> levelSetMap = map;
                subMenu.addSelection(map.getKey(), map.getValue().getName(), new Task<Void>() {
                    @Override
                    public Void run() {
                        gameFlow.runLevels(levelSetMap.getValue().getLevels());
                        Animation end = new KeyPressStoppableAnimation(ks, "space",
                                new EndScreen(gameFlow.getGameCompleted(), gameFlow.getScore()));
                        animationRunner.run(end);
                        if (hsTable.getRank(gameFlow.getScore()) < hsTable.size()) {
                            String playerName = dialog.showQuestionDialog("Name", "What is your name?", "");
                            hsTable.add(new ScoreInfo(playerName, gameFlow.getScore()));
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
            }
        }
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
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

        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            if (task == null) {
                animationRunner.run(subMenu);
                task = subMenu.getStatus();
                ((MenuAnimation<Task<Void>>) subMenu).restart();;
            }
            task.run();
            ((MenuAnimation<Task<Void>>) menu).restart();
            }
    }
}
