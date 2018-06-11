package com.wancient.mall.controller.portal;

import com.wancient.mall.common.Const;
import com.wancient.mall.enums.ResultEnum;
import com.wancient.mall.pojo.User;
import com.wancient.mall.service.IUserService;
import com.wancient.mall.util.ResultVoUtil;
import com.wancient.mall.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 前台用户controller
 *
 * @author: Wancient
 * @date: 2018/5/20
 * @time: 14:11
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<User> login(String username, String password, HttpSession session) {
        ResultVo<User> userResultVo = iUserService.login(username, password);
        if (userResultVo.getCode() == 0) {
            session.setAttribute(Const.CURRENT_USER, userResultVo.getData());
        }
        return userResultVo;
    }

    /**
     * 用户登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ResultVo<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ResultVoUtil.success(ResultEnum.LOGOUT_SUCCESS.getMessage());
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> register(User user) {
        return iUserService.register(user);
    }

    /**
     * 校验方法
     *
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "/check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ResultVoUtil.success(user);
        }
        return ResultVoUtil.error(ResultEnum.NEED_LOGIN);
    }

    /**
     * 找回密码提示问题
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }

    /**
     * 校验问题和答案
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "/forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);

    }

    /**
     * 找回密码的重置密码
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "/forget_rest_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return iUserService.forgetRestPassword(username, passwordNew, forgetToken);
    }

    /**
     * 登录状态修改密码
     *
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "/rest_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultVoUtil.error(ResultEnum.NEED_LOGIN);
        }
        return iUserService.resetPassword(user, passwordOld, passwordNew);
    }

    /**
     * 更新用户信息
     *
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "/update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<User> updateInformation(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ResultVoUtil.error(ResultEnum.NEED_LOGIN);
        }
        System.out.println(currentUser.toString());
        System.out.println(currentUser.getId());
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ResultVo<User> result = iUserService.updateInformation(user);
        if (result.getCode() == 0) {
            //更新session
            session.setAttribute(Const.CURRENT_USER, result.getData());
        }
        return result;
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "/get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<User> getInformation(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ResultVoUtil.error(ResultEnum.NEED_LOGIN);
        }
        return iUserService.getInformation(currentUser.getId());

    }
}
