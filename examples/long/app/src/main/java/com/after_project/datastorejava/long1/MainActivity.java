package com.after_project.datastorejava.long1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
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


        //SetLong Button click
        findViewById(R.id.DataStoreJavaSetLongButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;
                try {
                    Long my_single_long_value_here = 111111111113333333L;
                    testDataStore.setLong("single_long_key_name",my_single_long_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setLong failed");
                    }else{
                        System.out.println("setLong success");
                    }
                }

                error = null;
                try {
                    Long my_flowable_long_value_here = 2222222222224444444L;
                    testDataStore.setFlowableLong("flowable_long_key_name",my_flowable_long_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setFlowableLong failed");
                    }else{
                        System.out.println("setFlowableLong success");
                    }
                }

            }
        });

        //GetLong Button click
        findViewById(R.id.DataStoreJavaGetLongButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;

                try {

                    Long b1 = testDataStore.getLong("single_long_key_name");

                    System.out.println("getLong=" + (b1==null?"b1=null":"b1=="+b1));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getLong failed");
                    }else{
                        System.out.println("getLong success");
                    }
                }

                error = null;
                try {

                    Long b2 = testDataStore.getFlowableLong("flowable_long_key_name");

                    System.out.println("getFlowableLong=" + (b2==null?"b2=null":"b2=="+b2));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getFlowableLong failed");
                    }else{
                        System.out.println("getFlowableLong success");
                    }
                }

            }
        });
    }
}