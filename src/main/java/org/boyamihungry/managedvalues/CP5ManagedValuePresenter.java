package org.boyamihungry.managedvalues;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

/**
 * Created by patwheaton on 10/18/16.
 */
public class CP5ManagedValuePresenter<T extends Number> implements ValuePresenter {

    private final int MARGIN = 5;

    private ManagedValue<T> value;
    private PFont font;
    private PApplet app;


    public CP5ManagedValuePresenter(PApplet app, ManagedValue<T> value) {
        this(app, value, app.createFont("Arial", 14));
    }

    public CP5ManagedValuePresenter(PApplet app, ManagedValue<T> value, PFont font) {
        this.app = app;
        this.font = font;
        this.value = value;
    }



    @Override
    public PVector presentValue(PVector origin, PVector minimumSize, PVector maximumSize) {

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

        //app.text();
        int currentX = 0;
        int currentY = 0;
        // size of what is in this display
        PVector size = new PVector(0,0);
        app.text(value.getKey(), MARGIN, MARGIN);
        currentY += font.getSize() + MARGIN + MARGIN;


        for (ValueController<T> vc : value.getAvailableValueControllers()) {
            // TODO: create the checkbox. for now just indicate if it's the active one.
            if (value.getCurrentValueController().equals(vc)) {
                app.text("XXX", font.getGlyph('X').width + currentX, currentY);
            }
            currentX += font.getGlyph('X').width * 5;
            // draw the controller
            vc.draw(app);

        }


        value.getAvailableValueControllers().stream().forEach(c -> {});

        return size;

    }









}
