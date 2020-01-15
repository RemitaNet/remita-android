package com.remita.paymentsdk.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ochoge on 9/21/15.
 */
public class JsonUtil {

    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return builder.create();
    }

    public static <T> T fromJson(String json, Class<T> clazz) {

        return getGson().fromJson(json, clazz);
    }

    public static <T> String toJson(T object) {
        return getGson().toJson(object);
    }

    public static <T> List<T> fromJsonAsList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        return getGson().fromJson(json, type);
    }

    public static <T, S> T convert(S object, Class<T> tClass) {
        String json = toJson(object);
      return fromJson(json, tClass);
    }


    public static <T> List<T> fromJsonStringToList(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }
}


