package com.after_project.datastorejava.custompreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.datastore.preferences.core.Preferences;

import java.util.HashMap;
import java.util.Map;
public class DataStoreJavaPreferences extends Preferences {
    private HashMap<Key<?>,Object> m = new HashMap<>();
    @Nullable
    @Override
    public <T> T get(@NonNull Key<T> key) {
        return (T) m.get(key);
    }

    @Override
    public <T> boolean contains(@NonNull Key<T> key) {
        return m.containsKey(key);
    }

    @NonNull
    @Override
    public Map<Key<?>, Object> asMap() {
        return m;
    }

    void addValue(Preferences.Key<?> key, Object value){
        m.put(key,value);
    }
}