package com.hjc.demo.springboot.init.util;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class AvroUtil {
    public static ByteArrayOutputStream serializeToMemory(Schema schema, List<GenericRecord> records) throws IOException, IOException {
        DatumWriter<GenericRecord> datumWriter = new SpecificDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> fileWriter = new DataFileWriter<GenericRecord>(datumWriter);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //先现将Schema写入到内存中
        fileWriter.create(schema, out);
        //再开始追加多条GenericRecord记录
        for(GenericRecord record : records){
            fileWriter.append(record);
        }
        fileWriter.close();
        return out;
    }
}
