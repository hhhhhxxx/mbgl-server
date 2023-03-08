package com.hhhhhx.mbgl.utils;

import java.math.BigDecimal;

public class MoneyUtil {
    public static Double toDouble(Integer price) {

        BigDecimal divide = new BigDecimal(price).divide(new BigDecimal(100));

        return divide.doubleValue();
    }
}
