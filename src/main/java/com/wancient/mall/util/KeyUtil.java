package com.wancient.mall.util;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Wancient
 * Date: 2018/2/27
 * Time: 15:47
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random=new Random();
        //int s = random.nextInt(max) % (max - min + 1) + min;
        Integer number = random.nextInt(999999) % (999999 - 100000 + 1) + 100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }

    /**
     * 订单id
     * 格式：DD+时间+随机数
     * @return
     */
    public static synchronized String genOrderKey(){
        Random random=new Random();
        //int s = random.nextInt(max) % (max - min + 1) + min;
        Integer number = random.nextInt(999999) % (999999 - 100000 + 1) + 100000;
        return "DD"+System.currentTimeMillis()+String.valueOf(number);
    }
}
