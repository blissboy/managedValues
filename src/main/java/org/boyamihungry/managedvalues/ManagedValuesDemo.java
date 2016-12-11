package org.boyamihungry.managedvalues;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.*;

public class ManagedValuesDemo extends PApplet {


    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int POINTS = 200;
    public static final int POINT_SPREAD = 3;
    public static final int HISTORY_HEIGHT = 100;
    public static final PVector PLUS_ONE_X = new PVector(1,0);

    //private ManagedValuesControlPanel view;
    ManagedValueManager mgr;
    boolean setupComplete = false;

    List<Oscillator<? extends Number>> oscillators = new ArrayList<>();
    Map<String, List<Number>> pointMap = new HashMap<>();

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup()  {
        frameRate(33);
        //ControlP5 cp5 = new ControlP5(this);
        //view = new CP5ManagedValuesControlPanel("view",0,0);

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

        //mgr.addView(view);
        //view.addManagedValue(
        ManagedValue<Float> floatVar = mgr.createManagedValue("floatVar", 0f, 1000f, 100f, this);
        pointMap.put(floatVar.getKey(), new ArrayList<>(POINTS));
        //);
        //view.addManagedValue(
                //pointMap.put(mgr.createManagedValue("intVar", 0, 1000, 100, this).getKey(), new ArrayList<>(POINTS));
        //);

//        view.setPresenterForValue(
//                mgr.getManagedValue("floatVar"),
//                new SliderValueController(cp5,mgr.getManagedValue("floatVar")),
//                true);
//


        Oscillator sin99 = new SinusoidalOscillator(99f);
        floatVar.addValueController(new OscillatorValueController<>(floatVar, sin99));
        floatVar.addValueController(new OscillatorValueController<>(floatVar, new SinusoidalOscillator<Float>(200f)));

        try {
            floatVar.setValueController(floatVar.getAvailableValueControllers().stream().findFirst().get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //mgr.getManagedValue("floatVar").

        //view.addManagedValue(new BaseManagedValue<Float>("myFirstMV", new BaseManagedValue.Range<Float>(0f, 100f, 12f)));
        setupComplete = true;
    }

    @Override
    public void draw() {
        if ( setupComplete) {
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



            //view.show(new PVector(0,0), new PVector(100,500));
        }
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
