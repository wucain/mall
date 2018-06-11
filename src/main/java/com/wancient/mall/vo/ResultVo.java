package com.wancient.mall.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * http请求最外层返回对象
 * Created by IntelliJ IDEA.
 * User: Wancient
 * Date: 2018/2/25
 * Time: 18:22
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResultVo<T> implements Serializable {


    private static final long serialVersionUID = 4352852046722397283L;
    /**
     * 错误码
     **/
    private Integer code;

    /**
     * 提示信息
     **/
    private String msg;

    /**
     * 具体内容
     **/
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
