package com.after_project.datastorejava.integer;

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


        //SetInteger Button click
        findViewById(R.id.DataStoreJavaSetIntegerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;
                try {
                    Integer my_single_integer_value_here = 12;
                    testDataStore.setInteger("single_integer_key_name",my_single_integer_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setInteger failed");
                    }else{
                        System.out.println("setInteger success");
                    }
                }

                error = null;
                try {
                    Integer my_flowable_integer_value_here = 31;
                    testDataStore.setFlowableInteger("flowable_integer_key_name",my_flowable_integer_value_here);
                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setFlowableInteger failed");
                    }else{
                        System.out.println("setFlowableInteger success");
                    }
                }

            }
        });

        //GetInteger Button click
        findViewById(R.id.DataStoreJavaGetIntegerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;

                try {

                    Integer b1 = testDataStore.getInteger("single_integer_key_name");

                    System.out.println("getInteger=" + (b1==null?"b1=null":"b1=="+b1));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getInteger failed");
                    }else{
                        System.out.println("getInteger success");
                    }
                }


                try {

                    Integer b2 = testDataStore.getFlowableInteger("flowable_integer_key_name");

                    System.out.println("getFlowableInteger=" + (b2==null?"b2=null":"b2=="+b2));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getFlowableInteger failed");
                    }else{
                        System.out.println("getFlowableInteger success");
                    }
                }
            }

        });
    }
}