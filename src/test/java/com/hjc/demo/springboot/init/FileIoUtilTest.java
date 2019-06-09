package com.hjc.demo.springboot.init;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileIoUtilTest {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\test\\1.txt");
        FileWriter out = new FileWriter("E:\\test\\2.txt");
//        out.write("123");
        File file1 = new File("E:\\test\\2.txt");
//        IOUtils.copy(new FileInputStream(file), out,"gbk");
//        byte[] bytes = IOUtils.toByteArray(new FileReader(file), "gbk");
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "gbk");
        byte[] bytes = IOUtils.toByteArray(in);
        IOUtils.write(bytes, out);
        out.flush();
        out.close();
//        OutputStream out = new FileOutputStream("E:\\\\test\\\\2.txt");
//        FileUtils.copyFile(file, file1);

//        IOUtils.copy(new FileInputStream(file), out, "utf-8");

//        FileUtils.writeLines(file1, "UTF-8", Lists.newArrayList("asdf"));
        //
//        StringWriter writer = new StringWriter();
//        IOUtils.copy(new FileInputStream(file), writer, "utf-8");
//        String str = writer.toString();
//        System.out.println(str);
    }
}
