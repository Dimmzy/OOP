import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

public class EndScreen implements Animation {

    private boolean gameCompleted;
    private int finalScore;
    private KeyboardSensor keySensor;
    private boolean shouldStop;



    public EndScreen(boolean gameCompleted, int score, KeyboardSensor ks) {
        this.gameCompleted = gameCompleted;
        this.finalScore = score;
        this.keySensor = ks;
        this.shouldStop = false;
    }


    public void doOneFrame(DrawSurface d) {
        if (gameCompleted) {
            Color victoryGreen = new Color(96, 203, 86);
            d.setColor(victoryGreen);
            d.drawText(200,300,"You Win! Your score is " + Integer.toString(finalScore), 25);
        }
        else {
            Color defeatBlue = new Color(64, 120, 178);
            d.setColor(defeatBlue);
            d.drawText(200,300, "Game Over. Your score is " + Integer.toString(finalScore), 25);
        }
        if (keySensor.isPressed(KeyboardSensor.SPACE_KEY))
            this.shouldStop = true;
    }

    public boolean shouldStop() { return this.shouldStop; }

}
