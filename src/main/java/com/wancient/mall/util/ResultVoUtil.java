package com.wancient.mall.util;


import com.wancient.mall.enums.ResultEnum;
import com.wancient.mall.vo.ResultVo;

/**
 * Created by IntelliJ IDEA.
 * User: Wancient
 * Date: 2018/2/25
 * Time: 23:46
 */
public class ResultVoUtil {

    /**
     * 成功
     *
     * @param object
     * @return
     */
    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setMsg(ResultEnum.SUCCESS.getMessage());
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static ResultVo error(ResultEnum resultEnum) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(resultEnum.getCode());
        resultVo.setMsg(resultEnum.getMessage());
        return resultVo;
    }
}
