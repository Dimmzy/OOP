package scores;

import java.io.Serializable;

public class ScoreInfo implements Serializable {

    private String playerName;
    private int playerScore;

    public ScoreInfo(String name, int score) {
        this.playerName = name;
        this.playerScore = score;
    }

    public String getName() {
        return this.playerName;
    }

    public int getScore() {
        return this.playerScore;
    }
}