package com.gao.hr.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @Author:Gao
 * @Date:2020-05-05 15:53
 */
public class RandomUtil {

    private static final Integer MAX = 1000000;

    private static final Integer MIN = 100000;

    private static final Random random = new Random();

    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

    public static String getSixBitRandom() {
        return sixdf.format(random.nextInt(MAX-MIN)+MIN);
    }
}
