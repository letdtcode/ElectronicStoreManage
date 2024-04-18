package com.mascara.electronicstoremanage.utils;

import java.io.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 18/04/2024
 * Time      : 5:02 CH
 * Filename  : FileHandleUtils
 */
public class FileHandleUtils {
    private static FileHandleUtils instance = null;

    public static FileHandleUtils getInstance() {
        if (instance == null)
            instance = new FileHandleUtils();
        return instance;
    }

    private FileHandleUtils() {

    }

    public String copyFile(String nameImage, String urlOrigin) {
        File srcFile = new File(urlOrigin);
        File destFile = new File("upload/images/" + nameImage + ".png");

        InputStream fileInputStream = null;
        OutputStream fileOutputStream = null;

        if (srcFile.exists()) {
            try {
                fileInputStream = new FileInputStream(srcFile);
                fileOutputStream = new FileOutputStream(destFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int i = 0;
            try {
                while ((i = fileInputStream.read()) != -1) {
                    fileOutputStream.write(i);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null)
                        fileInputStream.close();
                    if (fileOutputStream != null)
                        fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return destFile.getPath();
    }
}
