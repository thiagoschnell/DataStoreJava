package com.after_project.datastorejava.custompreferences;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;

import io.reactivex.Single;



public class MainActivity extends AppCompatActivity {
    int add_count = 0;
    DataStoreJavaPreferences customPreferences1 = new DataStoreJavaPreferences();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customPreferences1.addValue(new Preferences.Key<>("key"),"hello customPreferences1");

        //AddPreferencesValue Button click
        findViewById(R.id.DataStoreJavaAddPreferencesValue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPreferences1.addValue(new Preferences.Key<>("add_key" + add_count),"value" + add_count);
                System.out.println("customPreferences1 data=" + customPreferences1.toPreferences().toString());
                add_count++;
            }
        });

        //DataStoreJavaMergePreferences Button click
        findViewById(R.id.DataStoreJavaMergePreferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStoreJavaPreferences customPreferences2 = new DataStoreJavaPreferences();
                customPreferences2.addValue(new Preferences.Key<>("key"),"hello customPreferences2");
                System.out.println("customPreferences2 data=" + customPreferences2.toPreferences().toString());

                MutablePreferences mutablePreferences = customPreferences1.toMutablePreferences();
                mutablePreferences.plusAssign(customPreferences2);
                Preferences result = Single.just(mutablePreferences).blockingGet();
                System.out.println("merge result=" + result.toPreferences().toString());

                try {
                    DataStoreJava testDataStore = new DataStoreJava();
                    testDataStore.datastore = new RxPreferenceDataStoreBuilder(MainActivity.this,"test").build();
                    testDataStore.setData(result);
                    System.out.println("testDataStore data=" + testDataStore.getData().toPreferences());
                } catch (Exception e) {
                    System.out.println("setData error message=" + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}