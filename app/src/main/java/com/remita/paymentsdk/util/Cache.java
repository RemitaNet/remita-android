package com.remita.paymentsdk.util;


import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by ochoge on 10/29/15.
 */
public interface Cache extends Serializable {

    @Deprecated
    <T extends Serializable> Cache put(String key, T value);

    @Deprecated
    <T extends Serializable> T get(String key, Class<T> tClass);

    <R extends Cache> R putBoolean(String key, Boolean value);

    <R extends Cache> R putInteger(String key, Integer value);

    <R extends Cache> R putLong(String key, Long value);

    <R extends Cache> R putFloat(String key, Float value);

    <R extends Cache> R putString(String key, String value);

    <T extends Serializable> Cache putSerializable(String key, T value);

    Boolean getBoolean(String key);

    Integer getInteger(String key);

    Long getLong(String key);

    Float getFloat(String key);

    String getString(String key);

    <T extends Serializable> T getSerializable(String key, Class<T> tClass);

    boolean containsKey(String key);

    void clear();

    void remove(String key);

    Map<String, ?> entries();

    Set<String> keys();

    Collection<?> values();
}
