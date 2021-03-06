package scores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * A class in charge of arranging/loading/saving the high scores table file.
 */
public class HighScoresTable implements Serializable {

    private List<ScoreInfo> highScores;
    private int maxCapacity;

    /**
     * Constructs the highScores object. Creates a list with all of the highScores in the passed size.
     * @param size The size of the list containing the highscores.
     */
    public HighScoresTable(int size) {
        this.highScores = new ArrayList<ScoreInfo>(size + 1);
        this.maxCapacity = size;
    }

    /**
     * Adds a score to the list, if it's not full, simply add it, if it is, sort by descending and add it to the end.
     * @param score The score to add to the list.
     */
    public void add(ScoreInfo score) {
        if (this.highScores.size() < maxCapacity) {
            this.highScores.add(score);
        } else {
            this.sortScores();
            this.highScores.remove(maxCapacity - 1);
            this.highScores.add(score);
        }
    }

    /**
     * @return Returns the size of the highscores list.
     */
    public int size() {
        return this.maxCapacity;
    }

    /**
     * @return Returns a sorted list of the highscores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.sortScores();
    }

    /**
     * Returns the location of the score in the high scores table after it's addition.
     * @param score The score we check the rank of.
     * @return Returns the position of the score on the table (1 => highest score, rank = size => lowest score,
     *         rank > size => score won't be added to the list.
     */
    public int getRank(int score) {
        if (this.size() == 0) {
            return 0;
        }
        for (ScoreInfo rank : this.highScores) {
            if (rank.getScore() < score) {
                return this.highScores.indexOf(rank);
            }
        }
        return this.highScores.size() + 1;
    }

    /**
     * Clears the high scores table.
     */
    public void clear() {
        this.highScores.clear();
    }


    /**
     * We'll use the loadFromFile method to load the file, and retrieve the needed information from it.
     * @param filename The file we retrieve table information from
     * @throws IOException Throws IO exception in case loading the file fails.
     */
    public void load(File filename) throws IOException {
        this.highScores = loadFromFile(filename).highScores;
        this.maxCapacity = loadFromFile(filename).size();
    }

    /**
     * Saves this table's data to the specified file.
     * @param filename The name of the file we'll save to.
     * @throws IOException throws IO exception in case saving the file fails.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream outputFile = null;
        try {
            outputFile = new ObjectOutputStream(new FileOutputStream(filename));
            outputFile.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputFile != null) {
                outputFile.close();
            }
        }
    }

    /**
     * Reads a high scores table from the file.
     * @param filename the file to load the table from.
     * @return returns the high scores table.
     * @throws IOException throws IO exception in case loading the file fails.
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        ObjectInputStream fileStream = null;
        HighScoresTable fileTable;
        try {
            fileStream = new ObjectInputStream(new FileInputStream(filename));
            fileTable =  (HighScoresTable) fileStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            fileTable = new HighScoresTable(10);
            fileTable.save(filename);
            return fileTable;
        } finally {
            if (fileStream != null) {
                fileStream.close();
            }
        }
        return fileTable;
    }

    /**
     * Sorts the array using the sortByScore nested class to compare two scores each time.
     * @return returns a list of the scores sorting in an descending order.
     */
    private List<ScoreInfo> sortScores() {
        /**
         * We'll use the Comparator Interface to sort by scores
         */
        class SortByScore implements Comparator<ScoreInfo> {
            /**
             * Compares scores to sort them.
             * @param scoreOne first compared score.
             * @param scoreTwo second compared score.
             * @return returns the highest one.
             */
            public int compare(ScoreInfo scoreOne, ScoreInfo scoreTwo) {
                return scoreTwo.getScore() - scoreOne.getScore();
            }
        }
        this.highScores.sort(new SortByScore());
        return this.highScores;
    }

    /**
     * @return String representation of the scores.
     */
    public String toString() {
        String scoresTable = "";
        for (ScoreInfo player : this.highScores) {
            scoresTable.concat(player.getName() + " : " + player.getScore());
            scoresTable.concat("\n");
        }
        return scoresTable;
    }

}

