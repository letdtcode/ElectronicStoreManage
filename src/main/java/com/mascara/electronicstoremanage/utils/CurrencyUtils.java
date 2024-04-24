package com.mascara.electronicstoremanage.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 23/04/2024
 * Time      : 6:21 CH
 * Filename  : CurrencyUtils
 */
public class CurrencyUtils {
    private Locale locale;
    private static CurrencyUtils instance = null;

    public static CurrencyUtils getInstance() {
        if (instance == null)
            instance = new CurrencyUtils(new Locale("vi", "VN"));
        return instance;
    }

    private CurrencyUtils(Locale locale) {
        this.locale = locale;
    }

    private CurrencyUtils() {

    }

    public String convertVietnamCurrency(double money) {
        Currency currency = Currency.getInstance("VND");
        DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(this.locale);
        df.setCurrency(currency);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(this.locale);
        numberFormat.setCurrency(currency);
        return numberFormat.format(money);
    }

//    public Double parseVietnamCurrency(final String amount) {
//        final NumberFormat format = NumberFormat.getNumberInstance(this.locale);
//        if (format instanceof DecimalFormat) {
//            ((DecimalFormat) format).setParseBigDecimal(true);
//        }
//        try {
//            return (Double) format.parse(amount.replaceAll("[^\\d.,]", ""));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
