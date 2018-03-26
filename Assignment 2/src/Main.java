/**
 * Just a simple driver class.
 */
import biuoop.AlphaChannelNotSupportedException;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.Random;

public class Main {

    /**
     * Driver Program.
     * @param args whatever.
     */
    public static void main(String[] args) {
        Line l1 = new Line(4, 0, 6, 10);
        Line l2 = new Line(0, 3, 10, 7);
        System.out.println("L1 Slope:" + l1.getSlope());
        System.out.println("L1 freeVal:" + l1.getFreeVal());
        System.out.println("L2 Slope:" + l2.getSlope());
        System.out.println("L2 freeVal:" + l2.getFreeVal());
        System.out.println("x Intersection:" + l1.xIntersect(l2));
        System.out.println("y Intersection:" + l2.yIntersect(l1.xIntersect(l2)));
        GUI gui = new GUI("LOL", 800, 600);
        DrawSurface surface = gui.getDrawSurface();
        surface.setColor(Color.BLACK);
        surface.drawLine(4,0,6,10);
        surface.drawLine(0,3,10,7);
        surface.setColor(Color.RED);
        Point intercept = l1.intersectionWith(l2);
        surface.fillCircle((int)intercept.getX(),(int)intercept.getY(),3);
        gui.show(surface);
    }
}
