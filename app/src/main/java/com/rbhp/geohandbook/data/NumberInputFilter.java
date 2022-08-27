package com.rbhp.geohandbook.data;

import android.text.InputFilter;
import android.text.Spanned;

public class NumberInputFilter implements InputFilter {
    private Integer min = 0;
    private Integer max = 0;

    public NumberInputFilter(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if ((dest.toString() + source.toString()).length() == 0) {
            return "";
        }
        Integer value = Integer.parseInt(dest.toString() + source);
        if (isInRange(value)) {
            return null;
        }
        return "";
    }

    private boolean isInRange(Integer value) {
        return min <= value && value <= max;
    }
}
