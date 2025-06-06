# DataStore Java for Android
<h6>Easy to store and access data at runtime
designed to small and medium data

# Cenaroius can you shold be use it
<h6>   develop any configuration
   <br>develop any settings
   <br>develop any options, etc
<br><br>
note:
need test to use with big data, or very large data</p>
</h6>

# Implementation
    // Preferences DataStore (SharedPreferences like APIs)

    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // optional - RxJava2 support
    implementation("androidx.datastore:datastore-preferences-rxjava2:1.1.7")

    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.1.7")
    

    implementation("androidx.room:room-runtime:2.7.1")
    annotationProcessor("androidx.room:room-compiler:2.7.1")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:2.7.1")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:2.7.1")
    
# Examples
        
<h3>Creating DataStoreJava</h3>
        
        final String DATASTORE_NAME = "test";

        DataStoreJava testDataStore = new DataStoreJava();
        testDataStore.datastore = new RxPreferenceDataStoreBuilder(this,DATASTORE_NAME).build();

<h3>SetString</h3>

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/string/app/src/main/java/com/after_project/datastorejava/string/MainActivity.java#L32-L33

<h3>GetString</h3>

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/string/app/src/main/java/com/after_project/datastorejava/string/MainActivity.java#L71-L73

<h3>SetInteger</h3>

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/integer/app/src/main/java/com/after_project/datastorejava/integer/MainActivity.java#L32-L33

GetInteger

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/integer/app/src/main/java/com/after_project/datastorejava/integer/MainActivity.java#L71-L73      
        
<h3>SetBoolean</h3>

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/boolean/app/src/main/java/com/after_project/datastorejava/boolean1/MainActivity.java#L32-L33
        
<h3>GetBoolean</h3>

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/boolean/app/src/main/java/com/after_project/datastorejava/boolean1/MainActivity.java#L71-L73

<h3>SetLong</h3>

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/long/app/src/main/java/com/after_project/datastorejava/long1/MainActivity.java#L28-L29
        
<h3>GetLong</h3>

https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/long/app/src/main/java/com/after_project/datastorejava/long1/MainActivity.java#L67-L69              

# Object

Create the Person Class first
          
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

Create the person object
          
https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/object/app/src/main/java/com/after_project/datastorejava/object/MainActivity.java#L99-L103

<h3>SetObject</h3>



https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/object/app/src/main/java/com/after_project/datastorejava/object/MainActivity.java#L106-L108

<h3>SetByteArray</h3>
          
https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/object/app/src/main/java/com/after_project/datastorejava/object/MainActivity.java#L122-L124         

<h3>GetObject</h3>
          
https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/object/app/src/main/java/com/after_project/datastorejava/object/MainActivity.java#L184-L186

<h3>getByteArray</h3>
       
https://github.com/thiagoschnell/DataStoreJava/blob/442add5d0d2197444e4591260c4851c9a4f699a9/examples/object/app/src/main/java/com/after_project/datastorejava/object/MainActivity.java#L201-L203


         
