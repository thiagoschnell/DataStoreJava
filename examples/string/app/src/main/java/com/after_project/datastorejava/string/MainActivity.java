package com.after_project.datastorejava.string;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;

public class MainActivity extends AppCompatActivity {
    final String DATASTORE_NAME = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("DataStore Java");

        //create the DataStoreJava
        DataStoreJava testDataStore = new DataStoreJava();
        testDataStore.datastore = new RxPreferenceDataStoreBuilder(this,DATASTORE_NAME).build();


        //SetString Button click
        findViewById(R.id.DataStoreJavaSetStringButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;
                try {
                    String my_single_string_value_here = "hello single";
                    testDataStore.setString("single_string_key_name",my_single_string_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setString failed");
                    }else{
                        System.out.println("setString success");
                    }
                }

                error = null;
                try {
                    String my_flowable_string_value_here = "hello flowable";
                    testDataStore.setFlowableString("flowable_string_key_name",my_flowable_string_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setFlowableString failed");
                    }else{
                        System.out.println("setFlowableString success");
                    }
                }

            }
        });

        //GetString Button click
        findViewById(R.id.DataStoreJavaGetStringButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;

                try {

                    String b1 = testDataStore.getString("single_string_key_name");

                    System.out.println("getString=" + (b1==null?"b1=null":"b1=="+b1));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getString failed");
                    }else{
                        System.out.println("getString success");
                    }
                }


                try {

                    String b2 = testDataStore.getFlowableString("flowable_string_key_name");

                    System.out.println("getFlowableString=" + (b2==null?"b2=null":"b2=="+b2));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getFlowableString failed");
                    }else{
                        System.out.println("getFlowableString success");
                    }
                }
            }

        });
    }
}