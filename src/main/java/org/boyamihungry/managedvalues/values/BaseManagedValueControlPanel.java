package org.boyamihungry.managedvalues.values;

import org.boyamihungry.managedvalues.ProcessingUtilities;
import org.boyamihungry.managedvalues.controllers.InputSink;
import org.boyamihungry.managedvalues.controllers.MouseClickWatcher;
import org.boyamihungry.managedvalues.controllers.ValueController;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patwheaton on 10/18/16.
 */
public class BaseManagedValueControlPanel<T extends Number>
        implements ManagedValueControlPanel,
        InputSink,
        MouseClickWatcher {

    private final float MARGIN = 5;
    private final PVector MARGIN_VECTOR = new PVector(MARGIN, MARGIN);

    private ManagedValue<T> value;
    private PFont font;
    private PApplet app;
    private PVector origin;
    private PVector drawnSize;

    List<MouseClickWatcher> mouseWatchers = new ArrayList<>();

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

        float currentX = MARGIN + origin.x;

        PVector currentYVector = origin.copy().add(MARGIN, MARGIN);

        currentYVector.add(0, ProcessingUtilities.drawText(app, "", currentYVector).y);
        currentYVector.add(0, drawValueHeader(currentYVector).y);
        currentYVector.add(0, drawRange(currentYVector).y);

        for (ValueController<T> vc : value.getAvailableValueControllers()) {
            // TODO: create the checkbox. for now just indicate if it's the active one.
            if (value.getCurrentValueController().equals(vc)) {
                ProcessingUtilities.drawText(
                        app,
                        "XXX",
                        new PVector(font.getGlyph('X').width + currentX, currentYVector.y)
                );
            }
            currentX += font.getGlyph('X').width * 5;
            PVector drawVCAt = new PVector(currentX, currentYVector.y);
            currentYVector.add(0, vc.draw(app, drawVCAt).y);
            currentX = MARGIN + origin.x;
        }
        currentYVector.add(0, ProcessingUtilities.drawText(
                app,
                "current managed value: " + value.getValue(),
                currentYVector).y
        );

        return currentYVector;

    }

    private PVector drawRange(PVector origin) {
        return ProcessingUtilities.drawText(
                app,
                "min:" + value.getRange().getMin() +
                        " max:" + value.getRange().getMax() +
                        " default: " + value.getRange().getDefault(),
                origin);
    }


    private PVector drawValueHeader(PVector origin) {
        return ProcessingUtilities.drawText(app, "Managed Value: " + value.getKey(), origin);
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
