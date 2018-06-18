package scores;

import java.io.Serializable;

/**
 * Class that holds the score information for each player.
 */
public class ScoreInfo implements Serializable {

    private String playerName;
    private int playerScore;

    /**
     * Constructs the score information.
     * @param name The name of the player.
     * @param score The score of the player.
     */
    public ScoreInfo(String name, int score) {
        this.playerName = name;
        this.playerScore = score;
    }

    /**
     * @return returns the player's name.
     */
    public String getName() {
        return this.playerName;
    }

    /**
     * @return returns the player's score.
     */
    public int getScore() {
        return this.playerScore;
    }
}