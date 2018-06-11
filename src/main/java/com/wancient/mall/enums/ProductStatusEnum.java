package com.wancient.mall.enums;


/**
 * 商品状态
 * Created by IntelliJ IDEA.
 * User: Wancient
 * Date: 2018/2/25
 * Time: 9:01
 */
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private  Integer code;

    private  String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
