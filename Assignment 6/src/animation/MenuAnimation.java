package animation;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class MenuAnimation<T> implements Menu<T>{

    private final static int X_TEXT = 300;
    private final static int Y_OFFSET = 40;
    private KeyboardSensor keySensor;
    private String menuTitle;
    private AnimationRunner animationRunner;
    private List<MenuOptions<T>> optionList;
    private T status;



    public MenuAnimation (String menuTitle, KeyboardSensor keySensor, AnimationRunner animationRunner) {
        this.menuTitle = menuTitle;
        this.keySensor = keySensor;
        this.animationRunner = animationRunner;
        this.optionList = new ArrayList<MenuOptions<T>>();
        this.status = null;
    }

    public void addSelection(String key, String message, T returnVal) {
        this.optionList.add(new MenuOptions<T>(key, message, returnVal));
    }


    public T getStatus() {
        return this.status;
    }


    public void doOneFrame(DrawSurface d, double dt) {
        int yText = 100;
        d.setColor(Color.RED);
        d.drawText(X_TEXT,yText,menuTitle,30);
        for(MenuOptions<T> menuOp : optionList) {
            yText += Y_OFFSET;
            d.drawText(X_TEXT, yText, menuOp.getKey().toUpperCase() + ". " + menuOp.getMessage(), 30);
        }
    }


    public boolean shouldStop() {
        return false;
    }
}
