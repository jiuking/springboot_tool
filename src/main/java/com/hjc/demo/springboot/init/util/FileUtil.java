package com.hjc.demo.springboot.init.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件写入或读取
 */
public class FileUtil {

    /**
     * 写文件
     *
     * @param filePathAndName 文件路径以及文件名
     * @param bytes    字节数
     */
    public static void writeFile(String filePathAndName, byte[] bytes) throws IOException {
        File file = new File(filePathAndName);
        if (!file.exists()) {
            file.createNewFile();
        }
        file = new File(filePathAndName);
        FileUtils.writeByteArrayToFile(file, bytes,true);
    }

    /**
     * 读取全量数据
     *
     * @param filePathAndName
     * @return
     * @throws IOException
     */
    public static byte[] readFile(String filePathAndName) throws IOException {
        File file = new File(filePathAndName);
        return FileUtils.readFileToByteArray(file);
    }
}
