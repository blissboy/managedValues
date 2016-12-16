package org.boyamihungry.managedvalues.controllers;

import org.boyamihungry.managedvalues.values.ManagedValue;
import org.boyamihungry.managedvalues.valuegenerators.Oscillator;

import java.util.concurrent.Callable;

/**
 * Created by patwheaton on 12/10/16.
 */
public class  OscillatorValueController<T extends Number> implements OscillatorUsingValueController <T>{


    final ManagedValue<T> value;
    final Oscillator<? extends Number> oscillator;
    Callable<T> getvalue;


    /**
     * value = (((max - min) / 2) + min)
     *
     *
     * min = 10, max = 100
     * ((100 - 10) / 2) + ((100 + 10) / 2) * osc
     *
     *
     *
     *
     * @param value
     * @param oscillator
     */
    public OscillatorValueController(ManagedValue<T> value, Oscillator<? extends Number> oscillator) {
        this.value = value;
        this.oscillator = oscillator;

        if (value.getRange().getMin() instanceof Float) {
            float halfRangeSpan = (value.getRange().getMax().floatValue() - value.getRange().getMin().floatValue()) / 2f;
            //System.out.println( "midpoint of " + value.getRange().getMax() + " and " + value.getRange().getMin() + " is " + midpointOfRange);
            float midpointOfRange = (value.getRange().getMax().floatValue() + value.getRange().getMin().floatValue()) / 2f;
            //System.out.println( "halfrangespan of " + value.getRange().getMax() + " and " + value.getRange().getMin() + " is " + halfRangeSpan);
            getvalue = new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return (T)(Float.valueOf(midpointOfRange + (halfRangeSpan * oscillator.getValue().floatValue())));
                }
            };
        } else if (value.getRange().getMin() instanceof Double) {
            double midpointOfRange = (value.getRange().getMax().doubleValue() - value.getRange().getMin().doubleValue()) / 2d;
            double halfRangeSpan = (value.getRange().getMax().doubleValue() + value.getRange().getMin().doubleValue()) / 2d;
            getvalue = new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return (T)(Double.valueOf(midpointOfRange + (halfRangeSpan * oscillator.getValue().doubleValue())));
                }
            };
        } else if (value.getRange().getMin() instanceof Integer) {
            int midpointOfRange = (value.getRange().getMax().intValue() - value.getRange().getMin().intValue()) / 2;
            int halfRangeSpan = (value.getRange().getMax().intValue() + value.getRange().getMin().intValue()) / 2;
            getvalue = new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return (T)(Integer.valueOf(midpointOfRange + (halfRangeSpan * oscillator.getValue().intValue())));
                }
            };
        } else if (value.getRange().getMin() instanceof Long) {
            long midpointOfRange = (value.getRange().getMax().longValue() - value.getRange().getMin().longValue()) / 2L;
            long halfRangeSpan = (value.getRange().getMax().longValue() + value.getRange().getMin().longValue()) / 2L;
            getvalue = new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return (T)(Long.valueOf(midpointOfRange + (halfRangeSpan * oscillator.getValue().longValue())));
                }
            };
        } else {
            throw new UnsupportedClassVersionError("Can't handle type of " + value.getRange().getMin().getClass().getName());
        }

    }

    @Override
    public Oscillator<? extends Number> getOscillator() {
        return oscillator;
    }

    /**
     *
     *
     * @return
     */
    @Override
    public T getValue() {
        try {
            return getvalue.call();
        } catch ( Exception e) {
            throw new RuntimeException(e);
        }
    }
}
