package com.remita.paymentsdk.util;

import android.content.Context;
import android.content.SharedPreferences;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ochoge on 10/29/15.
 */
public class SharedPreferencesCache implements Cache {
    private Context context;
    private String name;

    public SharedPreferencesCache(Context context, String name) {
        this.context = context;
        this.name = name;
    }

    public SharedPreferencesCache(Context context) {
        this(context, RIPGateway.Keys.GLOBAL_CACHE_KEY);
    }

    protected SharedPreferences getPreferences() {
        SharedPreferences preferences = null;
        if (this.context != null) {
            Context parentContext = context.getApplicationContext();
            preferences = parentContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        return preferences;
    }

    @Override
    @Deprecated
    public <T extends Serializable> Cache put(String key, T value) {
        SharedPreferences preferences = getPreferences();

        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();

            if (value instanceof Boolean) {
                putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                putInteger(key, (Integer) value);
            } else if (value instanceof Long) {
                putLong(key, (Long) value);
            } else if (value instanceof Float) {
                putFloat(key, (Float) value);
            } else if (value instanceof String) {
                putString(key, (String) value);
            } else {
                putSerializable(key, value);
            }

            editor.apply();
        }

        return this;
    }

    public SharedPreferencesCache putBoolean(String key, Boolean value) {
        SharedPreferences preferences = getPreferences();

        if (preferences != null) {
            if (value != null) {
                preferences
                        .edit()
                        .putBoolean(key, value)
                        .apply();
            }
        }

        return this;
    }

    public SharedPreferencesCache putInteger(String key, Integer value) {
        SharedPreferences preferences = getPreferences();

        if (preferences != null) {
            if (value != null) {
                preferences
                        .edit()
                        .putInt(key, value)
                        .apply();
            }
        }

        return this;
    }

    public SharedPreferencesCache putLong(String key, Long value) {
        SharedPreferences preferences = getPreferences();

        if (preferences != null) {
            if (value != null) {
                preferences
                        .edit()
                        .putLong(key, value)
                        .apply();
            }
        }
        return this;
    }

    public SharedPreferencesCache putFloat(String key, Float value) {
        SharedPreferences preferences = getPreferences();

        if (preferences != null) {
            if (value != null) {
                preferences
                        .edit()
                        .putFloat(key, value)
                        .apply();
            }
        }
        return this;
    }

    public SharedPreferencesCache putString(String key, String value) {
        SharedPreferences preferences = getPreferences();

        if (preferences != null) {
            if (value != null) {
                preferences
                        .edit()
                        .putString(key, value)
                        .apply();
            }
        }
        return this;
    }

    public Boolean getBoolean(String key) {
        Boolean value = false;

        SharedPreferences preferences = getPreferences();
        if (preferences != null && preferences.contains(key)) {
            value = preferences.getBoolean(key, false);
        }

        return value;
    }

    public Integer getInteger(String key) {
        Integer value = 0;

        SharedPreferences preferences = getPreferences();
        if (preferences != null && preferences.contains(key)) {
            value = preferences.getInt(key, 0);
        }

        return value;
    }

    public Long getLong(String key) {
        Long value = 0L;

        SharedPreferences preferences = getPreferences();
        if (preferences != null && preferences.contains(key)) {
            value = preferences.getLong(key, 0L);
        }

        return value;
    }

    public Float getFloat(String key) {
        Float value = 0.0f;

        SharedPreferences preferences = getPreferences();
        if (preferences != null && preferences.contains(key)) {
            value = preferences.getFloat(key, 0f);
        }

        return value;
    }

    public String getString(String key) {
        String value = StringUtils.EMPTY;

        SharedPreferences preferences = getPreferences();
        if (preferences != null && preferences.contains(key)) {
            value = preferences.getString(key, StringUtils.EMPTY);
        }

        return value;
    }

    @Override
    public <T extends Serializable> T getSerializable(String key, Class<T> tClass) {
        T value = null;

        SharedPreferences preferences = getPreferences();
        if (preferences != null && preferences.contains(key)) {
            String json = preferences.getString(key, "{}");
            value = JsonUtil.fromJson(json, tClass);
        }

        return value;
    }

    @Override
    @Deprecated
    public <T extends Serializable> T get(String key, Class<T> tClass) {
        T value = null;
        SharedPreferences preferences = getPreferences();
        if (preferences != null && preferences.contains(key)) {
            if (tClass.equals(Boolean.class)) {
                value = (T) getBoolean(key);
            } else if (value instanceof Integer) {
                value = (T) getInteger(key);
            } else if (value instanceof Long) {
                value = (T) getLong(key);
            } else if (value instanceof Float) {
                value = (T) getFloat(key);
            } else if (value instanceof String) {
                value = (T) getString(key);
            } else {
                value = getSerializable(key, tClass);
            }
        }
        return value;
    }

    @Override
    public <T extends Serializable> Cache putSerializable(String key, T value) {
        SharedPreferences preferences = getPreferences();
        if (preferences != null) {
            if (value != null) {
                String json = JsonUtil.toJson(value);
                preferences
                        .edit()
                        .putString(key, json)
                        .apply();
            }
        }
        return this;
    }

    @Override
    public boolean containsKey(String key) {
        SharedPreferences preferences = getPreferences();
        return preferences != null && preferences.contains(key);
    }

    @Override
    public void clear() {
        SharedPreferences preferences = getPreferences();

        if (preferences != null) {
            Map<String, ?> dataMap = entries();
            SharedPreferences.Editor editor = preferences.edit();
            for (String key : dataMap.keySet()) {
                editor.remove(key);
            }
            editor.apply();
        }
    }

    public Map<String, ?> entries() {
        Map<String, ?> map = new HashMap<>();
        SharedPreferences preferences = getPreferences();
        if (preferences != null) {
            map = preferences.getAll();
        }

        return map;
    }

    public Set<String> keys() {
        Map<String, ?> entries = entries();
        return entries.keySet();
    }

    public Collection<?> values() {
        Map<String, ?> entries = entries();
        return entries.values();
    }

    @Override
    public void remove(String key) {
        SharedPreferences preferences = getPreferences();
        if (preferences != null) {
            preferences.edit()
                    .remove(key)
                    .apply();
        }
    }
}
