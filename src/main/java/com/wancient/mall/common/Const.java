package com.wancient.mall.common;

/**
 * 常量类
 *
 * @author: Wancient
 * @date: 2018/5/20
 * @time: 15:35
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String MANAGE_USER = "manageUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";


    public interface Role {
        /**
         * 普通用户
         */
        int ROLE_CUSTOMER = 0;

        /**
         * 管理员
         */
        int ROLE_ADMIN = 1;
    }
}
