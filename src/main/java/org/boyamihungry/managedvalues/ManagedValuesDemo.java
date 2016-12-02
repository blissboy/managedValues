package org.boyamihungry.managedvalues;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PVector;

public class ManagedValuesDemo extends PApplet {


    public static final int WIDTH = 1440;
    public static final int HEIGHT = 1440;

    private ManagedView view;


    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup()  {
        super.setup();

        ControlP5 cp5 = new ControlP5(this);

        view = new CP5ManagedView("view",0,0);

        ManagedValueManager mgr = new ManagedValueManager() {

            @Override
            public String getName() {
                return "demo";
            }

            @Override
            public <T extends Number> boolean validateController(ValueController<T> controller) {
                return true;
            }
        };

        mgr.addView(view);


        view.addManagedValue(mgr.createManagedValue("floatVar", 0f, 1000f, 100f));
        view.addManagedValue(mgr.createManagedValue("intVar", 0, 1000, 100));

        view.setPresenterForValue(
                mgr.getManagedValue("floatVar"),
                new SliderValuePresenter(cp5,mgr.getManagedValue("floatVar")),
                true);

        ValueController sin99 = new SinusoidalOscillator(99f);
        mgr.getManagedValue("floatVar").addValueController(sin99);
        mgr.getManagedValue("floatVar").addValueController(new SinusoidalOscillator(200f));

        try {
            mgr.getManagedValue("floatVar").setValueController(sin99);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //mgr.getManagedValue("floatVar").

        //view.addManagedValue(new BaseManagedValue<Float>("myFirstMV", new BaseManagedValue.Range<Float>(0f, 100f, 12f)));

    }

    @Override
    public void draw() {
        super.draw();
        view.show(new PVector(0,0), new PVector(100,500));
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
