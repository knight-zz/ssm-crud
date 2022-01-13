package com.hh.ssm.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回Jason数据的类,链式编程思想
 * @author zh
 * @date 2022/01/12 17:14
 **/
@Data
public class Msg {

    //状态码 200成功 400失败
    private int code;
    //用户返回给浏览器的数据
    private String msg;
    // 添加的数据
    private Map<String, Object> data = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(400);
        result.setMsg("处理失败");
        return result;
    }

    //链式编程,将查询结果添加到返回消息中
    public Msg add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }
}
