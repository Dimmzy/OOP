import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;

public class SpriteCollection {

    private List<Sprite> spriteList;

    public SpriteCollection() {
        this.spriteList = new ArrayList();
    }
    public void addSprite(Sprite s){
        this.spriteList.add(s);
    }

    public void notifyAllTimePassed(){
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    public void drawAllOn(DrawSurface d){
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}