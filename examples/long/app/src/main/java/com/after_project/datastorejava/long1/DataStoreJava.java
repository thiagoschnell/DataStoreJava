package com.after_project.datastorejava.long1;
// Copyright (c) Thiago Schnell | https://github.com/thiagoschnell/DataStoreJava/blob/main/LICENSE
// Licensed under the MIT License.
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.rxjava2.RxDataStore;

import org.reactivestreams.Subscription;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.rxjava3.core.FlowableSubscriber;

public class DataStoreJava<T> {
    RxDataStore<Preferences> datastore;
    DataStoreJava() {
    }
    static class FlowableCallback implements FlowableSubscriber {
        @Override
        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Subscription s) {
        }
        @Override
        public void onNext(Object o) {
        }
        @Override
        public void onError(Throwable t) {
        }
        @Override
        public void onComplete() {
            this.onSuccess();
        }
        public void onSuccess() {
        }
    }
    static class SingleCallback  {
        /**
         * set true to stop inherited throwable
         */
        Boolean raiseThrowable = null;
        public void onNext(Object o) {
        }
        public void onComplete() {
            this.onSuccess();
        }
        public void onSuccess() {
        }
        public void onError(Throwable t) {
        }
    }
    private void singleExecutor(Single single, SingleCallback singleCallback) throws Exception {
        final Throwable[] errorEvent = {null};
        try{
            single.doOnEvent((object, throwable) -> {
                try{
                    if(throwable==null){
                        singleCallback.onNext(object);
                    }else{
                        singleCallback.onError((Throwable) throwable);
                    }
                }catch (Exception e){
                    errorEvent[0] = e;
                }
            }).blockingGet();
        }catch (Exception e){
            if(singleCallback.raiseThrowable!=null && singleCallback.raiseThrowable){
                throw new Exception(e);
            }
        }
        if(errorEvent[0]!=null){
            throw new Exception(errorEvent[0]);
        }
        singleCallback.onComplete();
    };
    void clear(FlowableCallback flowableCallback) {
        datastore
                .updateDataAsync(prefsIn -> {
                    MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
                    mutablePreferences.clear();
                    return Single.just(mutablePreferences);
                }).toFlowable().doOnEach(flowableCallback).blockingFirst();
    }
    void clear(SingleCallback singleCallback) throws Exception {
        singleExecutor( datastore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.clear();
            return Single.just(mutablePreferences);
        }), singleCallback);
    }
    @Override
    public String toString() {
        return datastore.data().blockingFirst().asMap().toString();
    }
    Preferences getData(){
        return datastore.data().blockingFirst();
    }
    void setData(Preferences preferences) throws Exception {
        SingleCallback singleCallback = new SingleCallback(){
            { raiseThrowable = true; }
        };
        singleExecutor( datastore.updateDataAsync(prefsIn -> {
            return Single.just(preferences.toMutablePreferences());
        }), singleCallback);
    }
    boolean isEmpty(){
        return datastore.data().blockingFirst().asMap().equals(new HashMap<>());
    }

    //SET METHODS
    //note: if set value null the preferences key will desapear
    protected void setString(String key, String value) throws Exception {
        setSingleValue(key,(T)value);
    }
    protected void setInteger(String key, Integer value) throws Exception {
        setSingleValue(key,(T)value);
    }
    protected void setLong(String key, Long value) throws Exception {
        setSingleValue(key,(T)value);
    }
    protected void setBoolean(String key, Boolean value) throws Exception {
        setSingleValue(key,(T)value);
    }
    protected void setByteArray(String key, byte[] value) throws Exception {
        setSingleValue(key,(T)value);
    }
    public void setObject(String key, T value) throws Exception {
        Preferences.Key<byte[]> prefKey = new Preferences.Key<>(key);
        byte[] bytes = Utils.convertObjectToBytes(value);
        setSingleValue(prefKey, bytes);
    }

    protected void setFlowableString(String key, String value) throws Exception {
        setFlowableValue(key,(T)value);
    }
    protected void setFlowableInteger(String key, Integer value) throws Exception {
        setFlowableValue(key,(T)value);
    }
    protected void setFlowableBoolean(String key, Boolean value) throws Exception {
        setFlowableValue(key,(T)value);
    }
    protected void setFlowableLong(String key, Long value) throws Exception {
        setFlowableValue(key,(T)value);
    }
    protected void setFlowableByteArray(String key, byte[] value) throws Exception {
        setFlowableValue(key,(T)value);
    }
    public void setFlowableObject(String key, T value) throws Exception {
        Preferences.Key<byte[]> prefKey = new Preferences.Key<>(key);
        byte[] bytes = Utils.convertObjectToBytes(value);
        setFlowableValue(prefKey, bytes);
    }


//REMOVEVALUE
    protected void removeValue(String key) throws Exception{
        Preferences.Key<T> prefKey = new Preferences.Key<>(key);
        setSingleValue(prefKey, null);
    }
    protected void removeFlowableValue(String key) throws Exception{
        Preferences.Key<T> prefKey = new Preferences.Key<>(key);
        setFlowableValue(prefKey, null);
    }


//GETOBJECT

    //[start] Get a object value
    protected <T> Object getObject(String key) throws Exception {
        byte[] b = getByteArray(key);
        if (b == null) return null;
        return Utils.convertBytesToObject(b);
    }
    protected byte[] getByteArray(String key) throws Exception {
        T data = getSingleValue(key);
        Assert(byte[].class,data);
        return (byte[]) data;
    }

    protected Object getFlowableObject(String key) throws Exception{
        byte[] b = getFlowableByteArray(key);
        if (b == null) return null;
        return Utils.convertBytesToObject(b);
    }

    protected byte[] getFlowableByteArray(String key) throws Exception{
        Object object = getFlowableValue(key);
        Assert(byte[].class,object);
        return (byte[]) object;
    }

    //[end] Get a object value


//GETSTRING

    //[start] get a string value
    protected String getFlowableString(String key) throws Exception{
        Object object = getFlowableValue(key);
        Assert(String.class,object);
        return (String) object;
    }
    protected String getString(String key) throws Exception {
        T data = getSingleValue(key);
        Assert(String.class,data);
        return (String) data;
    }
    //[end] get a string value


//GETINTEGER

    //[start] get a integer value
    protected Integer getFlowableInteger(String key) throws Exception {
        Object object = getFlowableValue(key);
        Assert(Integer.class,object);
        return (Integer) object;
    }
    protected Integer getInteger(String key) throws Exception {
        T data = getSingleValue(key);
        Assert(Integer.class,data);
        return (Integer) data;
    }
    //[end] get a integer value


//GETBOOLEAN

    //[start] get a boolean value
    protected Boolean getFlowableBoolean(String key) throws Exception {
        Object object = getFlowableValue(key);
        Assert(Boolean.class,object);
        return (Boolean) object;
    }
    protected Boolean getBoolean(String key) throws Exception {
        T data = getSingleValue(key);
        Assert(Boolean.class,data);
        return (Boolean) data;
    }
    //[end] get a boolean value


//GETLONG

    //[start] get a Long value
    protected Long getFlowableLong(String key) throws Exception {
        Object object = getFlowableValue(key);
        Assert(Long.class,object);
        return (Long) object;
    }
    protected Long getLong(String key) throws Exception {
        T data = getSingleValue(key);
        Assert(Long.class,data);
        return (Long) data;
    }
    //[end] get a Long value

/*##########################
 *                        ##
 *  Private methods       ##
 *                        ##
 ###########################
 */


    private Single<?> getSingleMap(Function<Preferences, ?> mapp){
        return datastore.data().firstOrError().map(mapp);
    }

    /*
     *
     *  Base methods SET VALUE
     *  NOTE: if set value null the preferences key will desapear
     * */
    private void setSingleValue(String key, T value) throws Exception{
        if(value==null){
            throw new Exception("set single value cannot be null");
        }
        Preferences.Key<T> prefKey = new Preferences.Key<>(key);
        setSingleValue(prefKey, value);
    }

    private <T> void setSingleValue(Preferences.Key<T> prefKey, T value) throws Exception {
        SingleCallback singleCallback = new SingleCallback(){
            { raiseThrowable = true; }
        };
        singleExecutor(datastore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(prefKey, value);
            return Single.just(mutablePreferences);
        }), singleCallback);
    }

    private void setFlowableValue(String key, T value) throws Exception{
        if(value==null){
            throw new Exception("set flowable value cannot be null");
        }
        Preferences.Key<T> prefKey = new Preferences.Key<>(key);
        setFlowableValue(prefKey, value);
    }

    private <T> void setFlowableValue(Preferences.Key<T> prefKey, T value) throws Exception {
        FlowableCallback flowableCallback = new FlowableCallback();
        datastore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            /*
            mutablePreferences = null;
             */
            mutablePreferences.set(prefKey, value);
            return Single.just(mutablePreferences);
        }).toFlowable().doOnEach(flowableCallback).blockingFirst();
    }

    /*
     *
     *  Base methods GET VALUE
     *
     * */
    private T getSingleValue(String key) throws Exception {
        Preferences.Key<T> prefKey = new Preferences.Key<>(key);
        return getSingleValue(prefKey);
    }
    private T getSingleValue(Preferences.Key<T> prefKey) throws Exception {
        final Object[] data = new Object[]{null};
        SingleCallback singleCallback = new SingleCallback(){
            { raiseThrowable = true;}
            int count = 0;
            @Override
            public void onNext(Object o) {
                data[count] = o;
                count++;
            }
        };
        singleExecutor(getSingleMap(getMappFunction(prefKey)), singleCallback);
        return (T) data[0];
    }
    private Object getFlowableValue(String key) throws Exception{
        Preferences.Key<T> prefKey = new Preferences.Key<>(key);
        return getFlowableValue(prefKey);
    }
    private Function<Preferences, T> getMappFunction(Preferences.Key<T> prefKey){
        return (preferences -> {
            if(!preferences.contains(prefKey)){
                throw new Exception("You are trying to get value from a key that not exists '"+prefKey.getName()+"'");
            }
            return preferences.get(prefKey);
        });
    }
    private Object getFlowableValue(Preferences.Key<T> prefKey) throws Exception{
        final Object[] result = {null};
        Flowable<Object> flowable = datastore.data().map(getMappFunction(prefKey));
        flowable.doOnEach(new FlowableCallback() {
                    int count = 0;
                    @Override
                    public void onNext(Object object) {
                        result[count] = object;
                        count++;
                    }
                })
                .blockingFirst();
        return result[0];
    }

    /*
     *
     *  Utility methods
     *
     * */
    private void Assert(Class clazz, Object object) {
        if(object!=null)
            try{
                if (clazz.equals(String.class)) {
                    assert (object instanceof String);
                }if (clazz.equals(Boolean.class)) {
                    assert(object instanceof Boolean);
                }else if (clazz.equals(Integer.class)) {
                    assert(object instanceof Integer);
                }else if (clazz.equals(Long.class)) {
                    assert(object instanceof Long);
                }else if (clazz.equals(byte[].class)) {
                    assert(object instanceof byte[]);
                }
            } catch (AssertionError e) {
                throw new RuntimeException("java.lang.AssertionError(Not a " + clazz.getSimpleName() + ")");
            }
    }
    static class Utils{

        // Convert byte[] to object
        static private Object convertBytesToObject(byte[] bytes) {
            InputStream is = new ByteArrayInputStream(bytes);
            try (ObjectInputStream ois = new ObjectInputStream(is)) {
                return ois.readObject();
            } catch (IOException | ClassNotFoundException ioe) {
                ioe.printStackTrace();
            }
            throw new RuntimeException();
        }

        // Convert object to byte[]
        static byte[] convertObjectToBytes(Object obj) {
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
                ois.writeObject(obj);
                return boas.toByteArray();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
            //throw new RuntimeException();
        }
    }
}