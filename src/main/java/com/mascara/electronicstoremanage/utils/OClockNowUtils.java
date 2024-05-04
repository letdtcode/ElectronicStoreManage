package com.mascara.electronicstoremanage.utils;

import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 04/05/2024
 * Time      : 4:26 CH
 * Filename  : OclockNowUtils
 */
public class OClockNowUtils implements Runnable {
    private Text lblTimeNow;

    public OClockNowUtils(Text lblTimeNow) {
        this.lblTimeNow = lblTimeNow;
    }

    @Override
    public void run() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String timeNow = null;
        while (true) {
            timeNow = dateFormat.format(new Date());
            lblTimeNow.setText(timeNow);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
