package com.mascara.electronicstoremanage.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.EAN13Writer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 26/04/2024
 * Time      : 7:09 CH
 * Filename  : BarCodeUtils
 */
public class BarCodeUtils {
    private static BarCodeUtils instance = null;

    public static BarCodeUtils getInstance() {
        if (instance == null)
            instance = new BarCodeUtils();
        return instance;
    }

    public void generateCode128BarcodeImage(String barcodeText, String path) {
        EAN13Writer barcodeWriter = new EAN13Writer();
        try {
            System.out.println(barcodeText);
            BitMatrix matrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 600, 300);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String readBarcodeValue(String path) {
        try {
            BufferedImage bf = ImageIO.read(new FileInputStream(path));
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println(result.getText());
        } catch (IOException | NotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
