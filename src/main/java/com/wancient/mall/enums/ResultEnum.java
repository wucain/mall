package com.wancient.mall.enums;


/**
 * 前端提示的消息
 * Created by IntelliJ IDEA.
 * User: Wancient
 * Date: 2018/2/26
 * Time: 23:27
 */
public enum ResultEnum {
    SUCCESS(0, "成功"),
    REGISTER_SUCCESS(0, "注册成功"),
    LOGIN_SUCCESS(0, "登录成功"),
    LOGOUT_SUCCESS(0, "登出成功"),
    PASSWORD_UPDATE_SUCCESS(0, "密码更新成功"),
    INFORMATION_UPDATE_SUCCESS(0, "个人信息更新成功"),

    ERROR(1, "出错"),
    PARAM_ERROR(1001, "参数错误"),
    NEED_LOGIN(1002, "请先登录后再操作"),
    USERNAME_NOT_EXIST(1003, "用户名不存在"),
    PASSWORD_IS_ERROR(1004, "密码错误"),
    ACCOUNT_IS_ABNORMAL(1005, "账号异常,请联系管理员！"),
    USERNAME_IS_EXIST(1006, "用户名已存在"),
    EMAIL_IS_EXIST(1007, "email已存在"),
    REGISTER_ERROR(1007, "注册失败"),
    FORGET_PWD_QUESTION_IS_NULL(1009, "找回密码的问题是空"),
    QUESTION_ANSWER_ERROR(1010, "问题的答案错误"),
    TOKEN_ERROR(1011, "token无效或过期"),
    UPDATE_PWD_FAILURE(1012, "修改密码失败"),
    UPDATE_INFORMATION_FAILURE(1013, "修改个人信息失败"),
    NOT_IS_MANAGE_USER(1015, "不是管理员账户"),
    CREATE_ERROR(1016, "创建失败"),

    PRODUCT_NOT_EXIST(10001, "商品不存在"),

    PRODUCT_STOCK_ERROR(10002, "库存不正确"),

    ORDER_NOT_EXIST(10003, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(10004, "订单详情不存在"),

    ORDER_STATUS_ERROR(10005, "订单状态不正确"),

    ORDER_UPDATE_FAIL(10006, "订单更新失败"),

    ORDER_DETAIL_EMPTY(10007, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(10008, "支付状态不正确"),

    CART_EMPTY(10009, "购物车为空"),

    ORDER_OWNER_ERROR(10010, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(10020, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(10021, "微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(10022, "订单取消成功"),

    ORDER_FINISH_SUCCESS(10023, "订单完结成功"),

    PRODUCT_STATUS_ERROR(10030, "商品状态不正确"),

    PRODUCT_ON_SEAL_SUCCESS(10031, "商品上架成功"),

    PRODUCT_OFF_SEAL_SUCCESS(10032, "商品下架成功"),

    PRODUCT_SAVE_SUCCESS(10032, "商品保存成功"),

    PRODUCT_CATEGORY_SAVE_SUCCESS(10033, "商品类目保存成功"),

    LOGIN_FAIL(10040, "登录失败, 登录信息不正确"),;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
