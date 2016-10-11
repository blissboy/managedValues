package org.boyamihungry.managedvalues;

import java.util.List;

/**
 * Created by patwheaton on 10/9/16.
 */
public class FloatManagedValue extends BaseManagedValue<Float> {

    private final String key;
    private final Range<Float> range;
    float value;

    public Float getValue() {
        return value;
    }
    public void setValue(Float f) {
        this.value = f;
    }
    public FloatManagedValue(String key, FloatRange range) {
        this.key = key;
        this.range = range;
        this.value = range.defaultValue;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Range<Float> getRange() {
        return range;
    }

    @Override
    public List<Float> getHistory() {
        return null;
    }

    @Override
    public String toString() {
        return "FloatManagedValue{" +
                "key='" + key + '\'' +
                ", range=" + range +
                '}';
    }

    public class FloatRange implements Range<Float> {

        final float max;
        final float min;
        final float defaultValue;

        public FloatRange(float min, float max, float defaultValue) {
            if (min < max && defaultValue <= max && defaultValue >= min) {
                this.max = max;
                this.min = min;
                this.defaultValue = defaultValue;
            } else {
                throw new IllegalArgumentException("rule violated: min < defaultValue < max");
            }
        }

        @Override
        public Float getMax() {
            return null;
        }

        @Override
        public Float getMin() {
            return null;
        }

        @Override
        public Float getDefault() {
            return null;
        }

        @Override
        public String toString() {
            return "FloatRange{" +
                    "max=" + max +
                    ", min=" + min +
                    ", defaultValue=" + defaultValue +
                    '}';
        }
    }


}
