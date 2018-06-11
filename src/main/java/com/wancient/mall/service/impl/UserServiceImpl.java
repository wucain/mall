package com.wancient.mall.service.impl;

import com.wancient.mall.common.Const;
import com.wancient.mall.common.TokenCache;
import com.wancient.mall.dao.UserMapper;
import com.wancient.mall.enums.ResultEnum;
import com.wancient.mall.pojo.User;
import com.wancient.mall.service.IUserService;
import com.wancient.mall.util.MD5Util;
import com.wancient.mall.util.ResultVoUtil;
import com.wancient.mall.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * User用户接口实现
 *
 * @author: Wancient
 * @date: 2018/5/20
 * @time: 14:22
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVo<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        //用户名不存在
        if (resultCount == 0) {
            return ResultVoUtil.error(ResultEnum.USERNAME_NOT_EXIST);
        }
        //密码登录md5
        String md5pwd = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5pwd);
        //密码错误
        if (user == null) {
            return ResultVoUtil.error(ResultEnum.PASSWORD_IS_ERROR);
        }
        //设置密码为null
        user.setPassword(StringUtils.EMPTY);

        return ResultVoUtil.success(user);
    }

    @Override
    public ResultVo<String> register(User user) {
        //检验用户名是否存在
        ResultVo<String> checkResult = checkValid(user.getUsername(), Const.USERNAME);
        if (!checkResult.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return checkResult;
        }
        //检验邮箱是否存在
        checkResult = checkValid(user.getEmail(), Const.EMAIL);
        if (!checkResult.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return checkResult;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //md5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ResultVoUtil.error(ResultEnum.REGISTER_ERROR);
        }
        return ResultVoUtil.success(ResultEnum.REGISTER_SUCCESS.getMessage());
    }

    @Override
    public ResultVo<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                //检验邮箱是否存在
                if (resultCount > 0) {
                    return ResultVoUtil.error(ResultEnum.EMAIL_IS_EXIST);
                }
            } else if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                //用户名不存在
                if (resultCount > 0) {
                    return ResultVoUtil.error(ResultEnum.USERNAME_IS_EXIST);
                }
            } else {
                return ResultVoUtil.error(ResultEnum.PARAM_ERROR);
            }
        } else {
            return ResultVoUtil.error(ResultEnum.PARAM_ERROR);
        }
        return ResultVoUtil.success();
    }

    @Override
    public ResultVo<String> selectQuestion(String username) {

        //检验用户名是否存在
        ResultVo<String> checkResult = checkValid(username, Const.USERNAME);
        if (!checkResult.getCode().equals(ResultEnum.USERNAME_IS_EXIST.getCode())) {
            return ResultVoUtil.error(ResultEnum.USERNAME_NOT_EXIST);
        }
        String question = userMapper.selectQuestionByUsername(username);

        if (StringUtils.isNotBlank(question)) {
            return ResultVoUtil.success(question);
        }
        return ResultVoUtil.error(ResultEnum.FORGET_PWD_QUESTION_IS_NULL);
    }

    @Override
    public ResultVo<String> checkAnswer(String username, String question, String answer) {

        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            //是正确的，生成token
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ResultVoUtil.success(forgetToken);
        }
        return ResultVoUtil.error(ResultEnum.QUESTION_ANSWER_ERROR);
    }

    @Override
    public ResultVo<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ResultVoUtil.error(ResultEnum.PARAM_ERROR);
        }
        //检验用户名是否存在
        ResultVo<String> checkResult = checkValid(username, Const.USERNAME);
        if (!checkResult.getCode().equals(ResultEnum.USERNAME_IS_EXIST.getCode())) {
            return checkResult;
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)) {
            return ResultVoUtil.error(ResultEnum.TOKEN_ERROR);
        }
        if (StringUtils.equals(token, forgetToken)) {
            //更新密码
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int resultCount = userMapper.updatePasswordByUsername(username, md5Password);
            if (resultCount > 0) {
                return ResultVoUtil.success();
            }
        } else {
            //token错误
            return ResultVoUtil.error(ResultEnum.TOKEN_ERROR);
        }
        return ResultVoUtil.error(ResultEnum.UPDATE_PWD_FAILURE);
    }

    @Override
    public ResultVo<String> resetPassword(User user, String passwordOld, String passwordNew) {
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (resultCount == 0) {
            //密码错误
            return ResultVoUtil.error(ResultEnum.PASSWORD_IS_ERROR);
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ResultVoUtil.error(ResultEnum.PASSWORD_UPDATE_SUCCESS);
        }
        return ResultVoUtil.error(ResultEnum.UPDATE_PWD_FAILURE);
    }

    @Override
    public ResultVo<User> updateInformation(User user) {
        //用户名username不能更新
        //email 校验,判断是否已经被其他 人使用了email
        int resultCount = userMapper.checkEmailByUserid(user.getEmail(), user.getId());
        if (resultCount > 0) {
            //email已经被别人使用
            return ResultVoUtil.error(ResultEnum.EMAIL_IS_EXIST);
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        updateUser.setUpdateTime(new Date());
        //BeanUtils.copyProperties(user, updateUser);
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ResultVoUtil.success(updateUser);
        }
        return ResultVoUtil.error(ResultEnum.UPDATE_INFORMATION_FAILURE);
    }

    @Override
    public ResultVo<User> getInformation(Integer userid) {
        User user = userMapper.selectByPrimaryKey(userid);
        if (user == null) {
            return ResultVoUtil.error(ResultEnum.USERNAME_NOT_EXIST);
        }
        user.setPassword(StringUtils.EMPTY);
        return ResultVoUtil.success(user);
    }


    //后台操作

    /**
     * 判断是否是管理员
     *
     * @param user
     * @return
     */
    @Override
    public ResultVo checkAdminRoler(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(ResultEnum.CREATE_ERROR);
    }
}
