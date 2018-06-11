package com.wancient.mall.util;

/**
 * Created by IntelliJ IDEA.
 * User: Wancient
 * Date: 2018/3/16
 * Time: 13:28
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个 金额 是否一致
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        }
        return false;
    }
}
