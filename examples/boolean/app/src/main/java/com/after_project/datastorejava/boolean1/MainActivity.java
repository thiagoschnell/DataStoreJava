package com.after_project.datastorejava.boolean1;

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


        //SetBoolean Button click
        findViewById(R.id.DataStoreJavaSetBooleanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;
                try {
                    Boolean my_single_boolean_value_here = true;
                    testDataStore.setBoolean("single_boolean_key_name",my_single_boolean_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setBoolean failed");
                    }else{
                        System.out.println("setBoolean success");
                    }
                }

                error = null;
                try {
                    Boolean my_flowable_boolean_value_here = false;
                    testDataStore.setFlowableBoolean("flowable_boolean_key_name",my_flowable_boolean_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setFlowableBoolean failed");
                    }else{
                        System.out.println("setFlowableBoolean success");
                    }
                }

            }
        });

        //GetBoolean Button click
        findViewById(R.id.DataStoreJavaGetBooleanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;

                try {

                    Boolean b1 = testDataStore.getBoolean("single_boolean_key_name");

                    System.out.println("getBoolean=" + (b1==null?"b1=null":"b1=="+b1));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getBoolean failed");
                    }else{
                        System.out.println("getBoolean success");
                    }
                }


                try {

                    Boolean b2 = testDataStore.getFlowableBoolean("flowable_boolean_key_name");

                    System.out.println("getFlowableBoolean=" + (b2==null?"b2=null":"b2=="+b2));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getFlowableBoolean failed");
                    }else{
                        System.out.println("getFlowableBoolean success");
                    }
                }
            }

        });
    }
}