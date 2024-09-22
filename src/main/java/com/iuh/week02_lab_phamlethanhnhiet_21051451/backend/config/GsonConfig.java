package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.config;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfig {
    public static Gson getGson() {
        return Converters.registerLocalDateTime(new GsonBuilder()).create();
    }
}
