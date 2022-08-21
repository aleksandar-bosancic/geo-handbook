package com.rbhp.geohandbook.data;

import androidx.databinding.InverseMethod;

public class Converter {
    private Converter() {
    }

    @InverseMethod("toLong")
    public static long toLong(String value) {
        return Long.parseLong(value);
    }

    public static String toString(long value) {
        return String.valueOf(value);
    }

    @InverseMethod("toDouble")
    public static double toDouble(String value) {
        return Double.parseDouble(value);
    }

    public static String toString(double value) {
        return String.valueOf(value);
    }

    public static String tempRoundedToString(double value) {
        return Math.round(value) + "°C";
    }

    public static String humidityToString(int value) {
        return value + "%";
    }
}
