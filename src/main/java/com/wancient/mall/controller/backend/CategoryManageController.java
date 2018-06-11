package com.wancient.mall.controller.backend;

import com.wancient.mall.common.Const;
import com.wancient.mall.enums.ResultEnum;
import com.wancient.mall.pojo.User;
import com.wancient.mall.service.IUserService;
import com.wancient.mall.util.ResultVoUtil;
import com.wancient.mall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 分类管理Controller
 *
 * @author: Wancient
 * @date: 2018/6/11
 * @time: 21:15
 */
@Controller
@RequestMapping("manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    /**
     * 添加
     *
     * @param session
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("/add")
    public ResultVo addCategory(HttpSession session, String categoryName,
                                @RequestParam(value = "parentId", defaultValue = "0") Integer parentId) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResultVoUtil.error(ResultEnum.NEED_LOGIN);
        }
        //检验是否是管理员
        return null;
    }

}
