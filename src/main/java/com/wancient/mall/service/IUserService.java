package com.wancient.mall.service;

import com.wancient.mall.pojo.User;
import com.wancient.mall.vo.ResultVo;

import javax.servlet.http.HttpSession;

/**
 * User用户service
 *
 * @author: Wancient
 * @date: 2018/5/20
 * @time: 14:20
 */
public interface IUserService {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    ResultVo<User> login(String username, String password);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    ResultVo<String> register(User user);

    /**
     * 校验
     *
     * @param str
     * @param type
     * @return
     */
    ResultVo<String> checkValid(String str, String type);


    /**
     * 根据用户名查询问题
     *
     * @param username
     * @return
     */
    ResultVo<String> selectQuestion(String username);

    /**
     * 校验问题和答案
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ResultVo<String> checkAnswer(String username, String question, String answer);


    /**
     * 找回密码的重置密码
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ResultVo<String> forgetRestPassword(String username, String passwordNew, String forgetToken);

    /**
     * 登录状态修改密码
     *
     * @param user
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    ResultVo<String> resetPassword(User user, String passwordOld, String passwordNew);


    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    ResultVo<User> updateInformation(User user);

    /**
     * 更新用户信息
     *
     * @param id
     * @return
     */
    ResultVo<User> getInformation(Integer id);


    //后台操作

    /**
     * 判断是否是管理员
     *
     * @param user
     * @return
     */
    public ResultVo checkAdminRoler(User user);

}
