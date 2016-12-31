package org.boyamihungry.managedvalues.values;

import org.boyamihungry.managedvalues.controllers.InputSink;
import org.boyamihungry.managedvalues.controllers.MouseClickWatcher;
import org.boyamihungry.managedvalues.controllers.ValueController;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import java.awt.event.MouseEvent;

/**
 * Created by patwheaton on 10/18/16.
 */
public class BaseManagedValueControlPanel<T extends Number> implements ManagedValueControlPanel, InputSink, MouseClickWatcher {

    private final float MARGIN = 5;

    private ManagedValue<T> value;
    private PFont font;
    private PApplet app;


    public BaseManagedValueControlPanel(PApplet app, ManagedValue<T> value) {
        this(app, value, app.createFont("Arial", 10));
    }

    public BaseManagedValueControlPanel(PApplet app, ManagedValue<T> value, PFont font) {
        this.app = app;
        this.font = font;
        app.textFont(font);
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

        app.pushMatrix();
        app.translate(origin.x, origin.y);
        float currentX = MARGIN;
        float currentY = app.textAscent() + app.textDescent();
        float maxXUsed = 0;

        String displayText = "Managed Value: " + value.getKey();
        app.text(displayText,currentX + MARGIN, currentY + MARGIN);
        currentY += app.textAscent() + app.textDescent() + MARGIN + MARGIN;
        maxXUsed = app.textWidth(displayText) + MARGIN;
        displayText = "\tmin:" + value.getRange().getMin() +
                " max:" + value.getRange().getMax() +
                " default: " + value.getRange().getDefault();
        app.text(displayText, currentX + MARGIN, currentY + MARGIN);
        currentY += app.textAscent() + app.textDescent() + MARGIN + MARGIN;
        maxXUsed = app.textWidth(displayText) + MARGIN;

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
            maxXUsed = delta.x + currentX > maxXUsed ? delta.x + currentX : maxXUsed;
            currentX = MARGIN;
        }
        currentY += (app.textAscent() + app.textDescent());
        app.text("current managed value: " + value.getValue(), currentX, currentY);

        app.popMatrix();

        return new PVector(maxXUsed, currentY);

    }

    @Override
    public MouseEvent mouseClicked(MouseEvent mouseEvent) {
        return mouseEvent;
    }

    /**
     * Method to turn off input to this controller, likely because some other controller wants to accept input as well.
     *
     * @return true if this sink is now disabled, false if not acceptable.
     */
    @Override
    public boolean disableSinkIfAcceptable() {
        return true;
    }
}
