package com.wancient.mall.controller.backend;

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
 * @author: Wancient
 * @date: 2018/5/26
 * @time: 23:08
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    /**
     * 后台管理登录
     *
     * @param session
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<User> login(HttpSession session, String username, String password) {

        ResultVo<User> userResultVo = iUserService.login(username, password);
        if (userResultVo.getCode() == 0) {
            User user = userResultVo.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN) {
                //登录的是管理员
                session.setAttribute(Const.MANAGE_USER, user);
                return userResultVo;
            } else {
                //不是管理员
                return ResultVoUtil.error(ResultEnum.NOT_IS_MANAGE_USER);
            }
        }
        return userResultVo;

    }
}
