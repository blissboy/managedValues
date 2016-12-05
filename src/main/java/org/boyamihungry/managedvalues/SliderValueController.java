package org.boyamihungry.managedvalues;

import controlP5.ControlBehavior;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.Slider;

/**
 * Created by patwheaton on 10/9/16.
 */
public class SliderValueController implements CP5Controller<Slider> {

    static String ID = "SLIDER_PRESENTER";

    private final Slider slider;
    ManagedValue<Float> mValue;
    private float localSliderValueCopy;

    SliderValueController(ControlP5 cp5, ManagedValue<Float> managedValue) {

        this.mValue = managedValue;
        this.localSliderValueCopy = managedValue.getValue();

        if (null != cp5.get(Slider.class, ID + mValue.getKey())) {
            slider = cp5.get(Slider.class, ID + mValue.getKey());
        } else {
            slider = cp5.addSlider(ID + mValue.getKey());
        }

        slider.setValue(localSliderValueCopy)
                .setMax(this.mValue.getRange().getMax())
                .setMin(this.mValue.getRange().getMin());

        slider.setBehavior(new ControlBehavior() {
            @Override
            public void update() {
                //mValue.setValue(slider.getValue());
                localSliderValueCopy = slider.getValue();
            }
        });
    }

    @Override
    public Float getValue() {
        return localSliderValueCopy;
    }

//    @Override
//    public PVector present(PVector origin, PVector minimumSize, PVector maximumSize) {
//        System.out.println(mValue);
//        return new PVector(0,0);
//    }

    @Override
    public Controller<Slider> getControl() {
        return slider;
    }

    /**
     * Presents a value at the
     *
     * @param origin
     * @param minimumSize
     * @param maximumSize
     * @return
     */
//    @Override
//    public PVector draw(PVector origin, PVector minimumSize, PVector maximumSize) {
//        slider.setPosition(origin.x, origin.y);
//        slider.setValue(mValue.getValue());
//        slider.setWidth((int)maximumSize.x);
//        slider.setHeight((int)maximumSize.y);
//        return maximumSize;
//    }

    //    @Override
//    public Controller<Slider> getControl() {
//        return slider;
//    }
}