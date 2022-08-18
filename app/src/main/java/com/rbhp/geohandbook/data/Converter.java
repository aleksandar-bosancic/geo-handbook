package com.rbhp.geohandbook.data;

import androidx.databinding.InverseMethod;

public class Converter {
    @InverseMethod("toLong")
    public static long toLong(String value){
        return Long.parseLong(value);
    }

    public static String toString(long value){
        return String.valueOf(value);
    }
}
