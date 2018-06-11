package animation;

import scores.ScoreInfo;
import scores.HighScoresTable;
import biuoop.DrawSurface;
import java.awt.Color;

public class HighScoresAnimation implements Animation {

    private static final int Y_TEXT_OFFSET = 40;
    private static final int X_TEXT = 300;
    private HighScoresTable highScoresTable;
    private String terminatingKey;
    private boolean stop;

    /**
     * Constructs the high scores animation object with the passed parameters.
     * @param scores The high scores table we'll print the information from
     * @param endKey The key the player will press to close the high scores animation.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey) {
        this.highScoresTable = scores;
        this.terminatingKey = endKey;
        this.stop = false;
    }


    public void doOneFrame(DrawSurface d, double dt) {
        int yText = 100;
        int playerRank = 0;
        d.setColor(Color.BLACK);
        d.drawText(X_TEXT, yText / 2, "Press \"" + this.terminatingKey.toUpperCase() + "\" to close", 15);
        for (ScoreInfo score : this.highScoresTable.getHighScores()) {
            playerRank++;
            switch(playerRank) {
                case(1):
                    d.setColor(Color.RED);
                    break;
                case(2):
                    d.setColor(Color.BLUE);
                    break;
                case(3):
                    d.setColor(Color.ORANGE);
                    break;
                default:
                    d.setColor(Color.BLACK);
            }
            d.drawText(X_TEXT, yText,Integer.toString(playerRank) + ". " + score.getName() + " - "
                    + score.getScore(), 15);
            yText += Y_TEXT_OFFSET;
        }
    }


    public boolean shouldStop() { return this.stop; }

}