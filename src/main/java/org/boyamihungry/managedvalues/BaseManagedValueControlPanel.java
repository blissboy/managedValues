package org.boyamihungry.managedvalues;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

/**
 * Created by patwheaton on 10/18/16.
 */
public class BaseManagedValueControlPanel<T extends Number> implements ManagedValueControlPanel {

    private final int MARGIN = 5;

    private ManagedValue<T> value;
    private PFont font;
    private PApplet app;


    public BaseManagedValueControlPanel(PApplet app, ManagedValue<T> value) {
        this(app, value, app.createFont("Arial", 14));
    }

    public BaseManagedValueControlPanel(PApplet app, ManagedValue<T> value, PFont font) {
        this.app = app;
        this.font = font;
        this.value = value;
    }



    @Override
    public PVector draw(PVector origin, PVector minimumSize, PVector maximumSize) {

        // ------------------------------------
        //   value name
        //   min ___  max ____ default ____
        //
        // ( ) value controller 1 draws here
        // ( ) value controller 2 draws here
        //         ....
        // ( ) value controller n draws here
        //
        // current value : ____
        // _____________________________________

        // current value controller has its box checked
        // checking box changes value controller

        // assume that we have been translated or whatevs, so we can just draw at 0,0

        int currentX = 0;
        int currentY = 0;
        float maxXUsed = 0;
        app.text(value.getKey(), MARGIN, MARGIN);
        currentY += font.getSize() + MARGIN + MARGIN;
        maxXUsed = app.textWidth(value.getKey()) + MARGIN;

        for (ValueController<T> vc : value.getAvailableValueControllers()) {
            // TODO: create the checkbox. for now just indicate if it's the active one.
            if (value.getCurrentValueController().equals(vc)) {
                app.text("XXX", font.getGlyph('X').width + currentX, currentY);
            }
            currentX += font.getGlyph('X').width * 5;
            // draw the controller
            app.pushMatrix();
            app.translate(currentX, currentY);
            PVector delta = vc.draw(app);
            app.popMatrix();
            currentY += delta.y;
            maxXUsed = delta.x > maxXUsed ? delta.x : maxXUsed;
        }

        return new PVector(maxXUsed, currentY);

    }
}
