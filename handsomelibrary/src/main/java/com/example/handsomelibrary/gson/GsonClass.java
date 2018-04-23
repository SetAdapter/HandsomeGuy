package com.example.handsomelibrary.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Gson数据 设置
 * Created by Stefan on 2018/4/23 13:52.
 */

public class GsonClass {

    public static Gson buildGson() {
        Gson gson = null;
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefault_0())
                    .registerTypeAdapter(int.class, new IntegerDefault_0())
                    .registerTypeAdapter(Double.class, new DoubleDefault_0())
                    .registerTypeAdapter(double.class, new DoubleDefault_0())
                    .registerTypeAdapter(Long.class, new LongDefault_0())
                    .registerTypeAdapter(long.class, new LongDefault_0())
                    .create();
        }
        return gson;
    }
}
