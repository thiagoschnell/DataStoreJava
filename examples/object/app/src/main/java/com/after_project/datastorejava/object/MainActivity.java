package com.after_project.datastorejava.object;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
class Test implements Serializable{
    String name;
    String lastname;
    public Test(){
    }
    public Test(String name, String lastname) {
        this.name=name;
        this.lastname=lastname;
    }
}
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    ArrayList<String> list;
    HashMap<String,Test> map;
    // if transient, JVM ignore this field for serialization
    private transient BigDecimal salary;
    public Person(String name, int age, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        list = new ArrayList<>();
        map= new HashMap<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        String mapString = "";
        Iterator<Map.Entry<String,Test>> myVeryOwnIterator = map.entrySet().iterator();
        while(myVeryOwnIterator.hasNext()) {
            HashMap.Entry<String, Test> entry = myVeryOwnIterator.next();
            mapString += " name=" + entry.getValue().name +" lastname=" + entry.getValue().lastname + " ; ";
        }
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", list=" + list +
                ", map=" + mapString +
                '}';
    }
}

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


        //SetObject Button click
        findViewById(R.id.DataStoreJavaSetObjectButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;

                Person personObject = new Person("test",11144, new BigDecimal(900));
                personObject.list.add("hello4");
                personObject.list.add("helloo5");
                personObject.map.put("name01",new Test("nameone","lastnameone"));
                personObject.map.put("name02",new Test("nametwo","lastnametwo"));

                try {

                    testDataStore.setObject("single_object_key_name",personObject);

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setObject failed");
                    }else{
                        System.out.println("setObject success");
                    }
                }

                try {

                    byte[] my_single_byteArray_value_here = DataStoreJava.Utils.convertObjectToBytes(personObject);

                    testDataStore.setByteArray("single_byteArray_key_name",my_single_byteArray_value_here);

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setByteArray failed");
                    }else{
                        System.out.println("setByteArray success");
                    }
                }



                try {

                    testDataStore.setFlowableObject("flowable_object_key_name",personObject);

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setFlowableObject failed");
                    }else{
                        System.out.println("setFlowableObject success");
                    }
                }

                try {

                    byte[] my_flowable_byteArray_value_here = DataStoreJava.Utils.convertObjectToBytes(personObject);

                    testDataStore.setFlowableByteArray("flowable_byteArray_key_name",my_flowable_byteArray_value_here);

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("setFlowableByteArray failed");
                    }else{
                        System.out.println("setFlowableByteArray success");
                    }
                }

            }
        });



        //GetObject Button click
        findViewById(R.id.DataStoreJavaGetObjectButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exception error = null;

                try{

                    Person p1 = (Person) testDataStore.getObject("single_object_key_name");

                    System.out.println("getObject=" + (p1==null?"p1=null":"p1="+p1));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getObject failed");
                    }else{
                        System.out.println("getObject success");
                    }
                }

                try{

                    byte[] p2 = testDataStore.getByteArray("single_byteArray_key_name");

                    System.out.println("getByteArray=" + (p2==null?"p2=null":"p2="+p2));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getByteArray failed");
                    }else{
                        System.out.println("getByteArray success");
                    }
                }



                try{

                    Person p3 = (Person) testDataStore.getFlowableObject("flowable_object_key_name");

                    System.out.println("getFlowableObject=" + (p3==null?"p3=null":"p3="+p3));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getFlowableObject failed");
                    }else{
                        System.out.println("getFlowableObject success");
                    }
                }

                try{

                    byte[] p4 = testDataStore.getFlowableByteArray("flowable_byteArray_key_name");

                    System.out.println("getFlowableByteArray=" + (p4==null?"p4=null":"p4="+p4));

                } catch (Exception e) {
                    error = e;
                }finally {
                    if(error!= null){
                        error.printStackTrace();
                        System.out.println("getFlowableByteArray failed");
                    }else{
                        System.out.println("getFlowableByteArray success");
                    }
                }

            }
        });



    }
}