package org.boyamihungry.managedvalues;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by patwheaton on 12/27/16.
 */
public class ProcessingUtilities {


    public static boolean mouseOverCircle(PApplet app, int x, int y, float diameter) {
      return (app.dist(app.mouseX, app.mouseY, x, y) < diameter*0.5);
    }

    /**
     *
     * @param app the processing PApplet that this is running within
     * @param x x coordinate of the top left corner of the rectangle
     * @param y y coordinate of the top left corner of the rectangle
     * @param width
     * @param height
     * @return
     */
    public static boolean mouseOverRect(PApplet app, int x, int y, int width, int height) {
      return (app.mouseX >= x && app.mouseX <= x+width && app.mouseY >= y && app.mouseY <= y+height);
    }

    /**
     *
     * @param app
     * @param text
     * @param location
     * @return the size it took to draw the text. This assumes it was written on one line.
     */
    public static PVector drawText(PApplet app, String text, PVector location) {
        app.text(text, location.x, location.y);
        return new PVector(app.textWidth(text), app.textAscent() + app.textDescent());
    }

}
