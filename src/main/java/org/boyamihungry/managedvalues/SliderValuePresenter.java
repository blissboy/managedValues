package org.boyamihungry.managedvalues;

import controlP5.ControlBehavior;
import controlP5.ControlP5;
import controlP5.Controller;
import controlP5.Slider;

/**
 * Created by patwheaton on 10/9/16.
 */
public class SliderValuePresenter implements CP5Presenter<Slider> {

    static String ID = "SLIDER_PRESENTER";

    private final Slider slider;
    ManagedValue<Float> mValue;
    private float localSliderValueCopy;

    SliderValuePresenter(ControlP5 cp5, ManagedValue<Float> managedValue) {

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
                mValue.setValue(slider.getValue());
                //localSliderValueCopy = slider.getValue();
            }
        });
    }

    @Override
    public void presentValue() {
        System.out.println(mValue);
    }

    @Override
    public Controller<Slider> getControl() {
        return slider;
    }


}
            ;