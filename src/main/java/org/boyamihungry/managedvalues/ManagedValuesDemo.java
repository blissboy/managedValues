package org.boyamihungry.managedvalues;

import org.boyamihungry.managedvalues.controllers.OscillatorValueController;
import org.boyamihungry.managedvalues.controllers.ValueController;
import org.boyamihungry.managedvalues.valuegenerators.Oscillator;
import org.boyamihungry.managedvalues.valuegenerators.SinusoidalOscillatorBuilder;
import org.boyamihungry.managedvalues.values.ManagedValue;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ManagedValuesDemo extends PApplet {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int POINTS = 200;
    public static final int POINT_SPREAD = 3;
    public static final int HISTORY_HEIGHT = 100;

    ManagedValueManager mgr;

    List<Oscillator<? extends Number>> oscillators = new ArrayList<>();
    Map<String, List<Number>> pointMap = new HashMap<>();

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup()  {
        frameRate(5);
        mgr = new ManagedValueManager() {
            @Override
            public String getName() {
                return "demo";
            }
            @Override
            public <T extends Number> boolean validateController(ValueController<T> controller) {
                return true;
            }
        };

        ManagedValue<Float> floatVar = mgr.createManagedValue("floatVar", 0f, 1000f, 100f, this);
        pointMap.put(floatVar.getKey(), new ArrayList<>(POINTS));

        ManagedValue<Float> frameBasedVar = mgr.createManagedValue("frameBasedVar", 0f, 1000f, 100f, this);
        pointMap.put(frameBasedVar.getKey(), new ArrayList<>(POINTS));


        // add a time based oscillator
        Oscillator sin99 = new SinusoidalOscillatorBuilder().withFrequency(99f).build();
        floatVar.addValueController(new OscillatorValueController<>(floatVar, sin99));

        // add a frame based oscillator
        Oscillator<Float> framesOsc = new SinusoidalOscillatorBuilder<>()
                .withFrequency(99f)
                .withName("frame based 99")
                .withTimecodeGetter(
                new Callable<Long>() {
                    @Override
                    public Long call() throws Exception {
                        return Long.valueOf(frameCount);
                    }
                }).build();

        frameBasedVar.addValueController(new OscillatorValueController<>(frameBasedVar, framesOsc));
        //floatVar.addValueController(new OscillatorValueController<>(floatVar, new SinusoidalOscillatorBuilder<>().withFrequency(200f).build()));

        try {
            floatVar.setValueController(floatVar.getAvailableValueControllers().stream().findFirst().get());
            frameBasedVar.setValueController(frameBasedVar.getAvailableValueControllers().stream().findFirst().get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw() {
        background(190);
        PVector origin = new PVector(0, 0);
        mgr.getManagedValues().forEach(mv -> {
            pushStyle();
            pushMatrix();
            translate(origin.x, origin.y);
            PVector howMuchTheyDrew = mv.drawControlPanel();
            popMatrix();
            noFill();
            rectMode(CORNER);
            rect(origin.x,
                    origin.y,
                    howMuchTheyDrew.x * 1.05f,
                    howMuchTheyDrew.y * 1.05f);
            popStyle();
            origin.add(0, howMuchTheyDrew.y * 1.1f);
            pointMap.get(mv.getKey()).add(mv.getValue());
            if (pointMap.get(mv.getKey()).size() > POINTS) {
                pointMap.get(mv.getKey()).remove(0);
            }
        });

        // draw the history of the mvs
        mgr.getManagedValues().forEach(mv -> {
            pushMatrix();
            noFill();
            rectMode(CORNER);
            rect(origin.x, origin.y, POINTS * POINT_SPREAD, HISTORY_HEIGHT);
            PVector historyX = new PVector(0, 0);
            translate(origin.x, origin.y);
            PVector lastVal =  new PVector(0f,0F);
            pointMap.get(mv.getKey()).stream().map(
                    n -> HISTORY_HEIGHT * ((n.floatValue() - mv.getRange().getMin().floatValue()) / (mv.getRange().getMax().floatValue() - mv.getRange().getMin().floatValue())))
                    .forEach(number -> {
                        point(historyX.x, number);
                        line(historyX.x, number, historyX.x - 1, lastVal.y);
                        historyX.add(POINT_SPREAD,0);
                        lastVal.y = number;
                    });

            popMatrix();
            origin.add(0,HISTORY_HEIGHT + 5);
        });
    }


    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"org.boyamihungry.managedvalues.ManagedValuesDemo"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }

}
