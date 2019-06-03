package com.hjc.demo.springboot.init;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.IOException;
import java.util.List;

public class AvroTest {
    public static void main(String[] args) {

    }
    public static void serializeAvroToFile(List<User> userList, String fileName) throws IOException {
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
//        dataFileWriter.create(userList.get(0).getSchema(), new File(fileName));
        for (User user: userList) {
            dataFileWriter.append(user);
        }
        dataFileWriter.close();
    }
}
class User{
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}