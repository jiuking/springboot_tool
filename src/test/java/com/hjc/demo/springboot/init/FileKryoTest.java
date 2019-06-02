package com.hjc.demo.springboot.init;

import com.esotericsoftware.kryo.Kryo;
import com.hjc.demo.springboot.init.util.FileUtil;
import com.hjc.demo.springboot.init.util.KryoUtil;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileKryoTest {
    public static void main(String[] args) throws IOException {
        /*for (int i = 0; i < 2; i++) {
            Te te = new Te();
            te.setKey(""+i);
            te.setValue("张三");
            byte[] bytes = KryoUtil.obj2ByteArray(te);
            FileUtil.writeFile("E:\\test\\1.bin", bytes);

        }*/

        byte[] bytes1 = FileUtil.readFile("E:\\test\\1.bin");
        System.out.println(bytes1.length);
        List<Te> te1 = KryoUtil.byteArray2Obj(bytes1, Te.class);
        System.out.println(te1);
    }
}
@Data
class Te{
    public Te() {

    }
    private String key;
    private String value;
}
