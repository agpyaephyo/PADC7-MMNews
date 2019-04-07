package com.padcmyanmar.padc7.mmnews.persistence.typeconverters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListStringConverter {

    @TypeConverter
    public static String fromListToJson(List<String> images) {
        return new Gson().toJson(images);
    }

    @TypeConverter
    public static List<String> fromJsonToList(String jsonImages) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(jsonImages, listType);
    }
}
